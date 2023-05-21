package quan.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import quan.common.dto.auth.AuthenticationRequest;
import quan.common.dto.cart.CartRequest;
import quan.common.exceptions.AlreadyExistException;
import quan.common.exceptions.NotFoundException;
import quan.customerservice.entity.Customer;
import quan.customerservice.feign.AuthClient;
import quan.customerservice.feign.CartClient;
import quan.customerservice.feign.ImageClient;
import quan.customerservice.repository.CustomerRepository;
import quan.customerservice.service.CustomerService;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ImageClient imageClient;
    private final AuthClient authClient;
    private final CartClient cartClient;
    @Override
    public Customer registration(Customer customer) {
        String password = customer.getPassword();
        customer.setPassword(new BCryptPasswordEncoder().encode(password));
        if(customerRepository.findByUsername(customer.getUsername()) != null){
            throw new AlreadyExistException("User name has exist");
        }
        Customer cusomer = customerRepository.save(customer);
        authClient.authenticate(new AuthenticationRequest(customer.getUsername(), password));
        cartClient.createCart(new CartRequest(customer.getId()));
        return customer;
    }

    @Override
    public Customer uploadImage(MultipartFile image, Long customerId) {
        Customer customer = getById(customerId);
        if(image != null || image.isEmpty()){
            String imageUrl = imageClient.uploadImage(image);
            customer.setImageUrl(imageUrl);
        }
        return customer;
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("customer not found"));
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
