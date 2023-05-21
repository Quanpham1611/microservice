package quan.customerservice.service;

import quan.customerservice.entity.ShippingAddress;

import java.util.List;

public interface ShippingAddressService {
    ShippingAddress createShippingAddress(Long customerId, ShippingAddress address);
    ShippingAddress getShippingAddressById(Long id);
    List<ShippingAddress> getAllShippingAddressesByCustomerId(Long customerId);
    ShippingAddress updateShippingAddress(Long id, ShippingAddress addressDetails);
    void deleteShippingAddress(Long id);
}
