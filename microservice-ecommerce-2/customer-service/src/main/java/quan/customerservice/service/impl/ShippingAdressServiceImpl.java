package quan.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import quan.common.exceptions.NotFoundException;
import quan.common.utils.UpdatingUtil;
import quan.customerservice.entity.Customer;
import quan.customerservice.entity.ShippingAddress;
import quan.customerservice.repository.ShippingAdressRepository;
import quan.customerservice.service.ShippingAddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingAdressServiceImpl implements ShippingAddressService {
    private final ShippingAdressRepository shippingAdressRepository;

    private final CustomerServiceImpl customerService;
    @Override
    public ShippingAddress createShippingAddress(Long customerId, ShippingAddress address) {
        Customer customer = customerService.getById(customerId);
        address.setCustomer(customer);
        return shippingAdressRepository.save(address);
    }

    @Override
    public ShippingAddress getShippingAddressById(Long id) {
        return shippingAdressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shipping address not found "));
    }

    @Override
    public List<ShippingAddress> getAllShippingAddressesByCustomerId(Long customerId) {
        return shippingAdressRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public ShippingAddress updateShippingAddress(Long id, ShippingAddress addressDetails) {
        ShippingAddress address = getShippingAddressById(id);
        BeanUtils.copyProperties(addressDetails, address, UpdatingUtil.getNullPropertyNames(addressDetails));
        return shippingAdressRepository.save(address);
    }

    @Override
    public void deleteShippingAddress(Long id) {
        shippingAdressRepository.deleteById(id);
    }
}
