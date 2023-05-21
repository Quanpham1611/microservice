package quan.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quan.customerservice.dto.request.WishlistRequest;
import quan.customerservice.entity.Customer;
import quan.customerservice.entity.WishList;
import quan.customerservice.feign.ProductClient;
import quan.customerservice.repository.WishListRepository;
import quan.customerservice.service.CustomerService;
import quan.customerservice.service.WishlistService;
@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishlistService {
    private final WishListRepository wishListRepository;
    private final CustomerService customerService;
    private final ProductClient productClient;
    @Override
    public WishList create(WishlistRequest wishlistRequest) {
        WishList wishList = new WishList();
        Customer customer = customerService.getById(wishlistRequest.getCustomerId());
        Long productId = productClient.getProductById(wishlistRequest.getProductId()).getBody().getId();
        wishList.setCustomer(customer);
        wishList.setProductId(productId);
        return wishListRepository.save(wishList);
    }

    @Override
    public Page<WishList> getAllByCustomerId(Long id, Pageable pageable) {
        return wishListRepository.findAllByCustomer_Id(id, pageable);
    }

    @Override
    public void delete(Long id) {
        wishListRepository.deleteById(id);
    }
}
