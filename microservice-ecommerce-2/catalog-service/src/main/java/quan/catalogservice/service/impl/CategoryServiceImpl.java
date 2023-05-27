package quan.catalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import quan.catalogservice.entity.Banner;
import quan.catalogservice.entity.Category;
import quan.catalogservice.entity.Product;
import quan.catalogservice.repository.BannerRepository;
import quan.catalogservice.repository.CategoryRepository;
import quan.catalogservice.repository.ProductRepository;
import quan.catalogservice.service.CategoryService;
import quan.common.exceptions.CategoryException;
import quan.common.feign.ImageClient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final BannerRepository bannerRepository;
    private final ImageClient imageClient;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @Override
    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findAllByCategory_Id(categoryId, pageable);
    }

    @Override
    public Category setParentCategory(Long parentId, Category category) {
        if(parentId != null){
            Category parentCategory = getCategoryById(parentId);
            category.setParentCategory(parentCategory);
            if(parentCategory.getLevel() == 3){
                throw new CategoryException("You can not choose this category");
            }
            category.setLevel(parentCategory.getLevel() + 1);
        } else {
            category.setLevel(1);
        }
        return category;
    }

    private Category update(Long id, Category updatedCategory) {
        Category category = getCategoryById(id);
        category.setName(updatedCategory.getName());
        return category;
    }

    @Override
    public Category updateCategory(Long id, Long parentCategoryId, Category updatedCategory) {
        Category category = update(id, updatedCategory);
        setParentCategory(parentCategoryId, category);

//      Remove child from old parent category
        Category oldParent = category.getParentCategory().removeChild(category.getId());
        categoryRepository.save(oldParent);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = update(id, updatedCategory);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteBanner(Long id) {
        bannerRepository.deleteById(id);
    }
    private List<Banner> uploadAndReturnBanners(Category category, MultipartFile[] banners) {
        List<Banner> bannerList = new ArrayList<>();
        for (MultipartFile banner : banners) {
            String fileSrc = imageClient.uploadImage(banner);
            Banner bnr = bannerRepository.save(new Banner(fileSrc, category));
            bannerList.add(bnr);
        }
        return bannerList;
    }
    @Override
    public Category uploadBanners(Long id, MultipartFile[] banners) {
        Category category = getCategoryById(id);
        if(category.getLevel() != 1) {
            throw new IllegalArgumentException("A category must have 1 level");
        }
        if(category.getBanners().size() >= 3) {
            throw new IllegalArgumentException("A category can have only 3 banners");
        }
        List<Banner> uploadedBanners = uploadAndReturnBanners(category, banners);
        category.setBanners(uploadedBanners);
        return category;

    }
}
