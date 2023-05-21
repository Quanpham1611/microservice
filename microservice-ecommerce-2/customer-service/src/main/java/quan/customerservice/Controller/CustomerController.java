package quan.customerservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import quan.customerservice.dto.request.CustomerRequest;
import quan.customerservice.dto.request.ShippingAddressRequest;
import quan.customerservice.dto.response.CustomerResponse;
import quan.customerservice.dto.response.ShippingAddressResponse;
import quan.customerservice.entity.ShippingAddress;
import quan.customerservice.mapper.CustomerMapper;
import quan.customerservice.mapper.ShippingAddressMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerMapper customerMapper;
    private final ShippingAddressMapper shippingAddressMapper;
    @PostMapping
    public ResponseEntity<CustomerResponse> registration(@Valid @RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(customerMapper.registration(customerRequest));
    }
    @PostMapping("/{customerId}/upload")
    public ResponseEntity<CustomerResponse> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(customerMapper.uploadImage(image, customerId));
    }
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(customerMapper.getAll(pageable));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable("customerId") Long id) {
        return ResponseEntity.ok(customerMapper.getById(id));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<CustomerResponse> getByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(customerMapper.getByUsername(username));
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("customerId") Long id) {
        customerMapper.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/shipping-address/{id}")
    public ResponseEntity<ShippingAddressResponse> getShippingAddressById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shippingAddressMapper.getShippingAddressById(id));
    }
    @GetMapping("/{id}/shipping-address/")
    public ResponseEntity<List<ShippingAddressResponse>> getAllShippingAddressesByCustomerId(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(shippingAddressMapper.getAllShippingAddressesByCustomerId(customerId));
    }
    @PutMapping("/shipping-address/{id}")
    public ResponseEntity<ShippingAddressResponse> updateShippingAddress(@PathVariable("id") Long id,
                                                                         @Valid @RequestBody ShippingAddressRequest address) {
        return ResponseEntity.ok(shippingAddressMapper.updateShippingAddress(id, address));
    }
    @DeleteMapping("/shipping-address/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable("id") Long id) {
        shippingAddressMapper.deleteShippingAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
