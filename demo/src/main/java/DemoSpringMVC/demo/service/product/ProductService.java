package DemoSpringMVC.demo.service.product;

import DemoSpringMVC.demo.domain.product.CreateProduct;
import DemoSpringMVC.demo.domain.product.Product;
import DemoSpringMVC.demo.entity.ProductEntity;
import DemoSpringMVC.demo.repository.ProductRepository;
import DemoSpringMVC.demo.service.category.ICategoryService;
import DemoSpringMVC.demo.service.file.IFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IFileService fileService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByCategoryId(long categoryId) {
        return null;
    }

    @Override
    public Product getById(long id) {
        return modelMapper.map(productRepository.findById(id).get(), Product.class);
    }

    @Override
    public ProductEntity getByProductSlug(String slug) {
        return productRepository.findBySlug(slug).get();
    }

    @Override
    public void create(CreateProduct newProduct, long categoryId) throws IOException {
        ProductEntity productEntity = modelMapper.map(newProduct, ProductEntity.class);
        productEntity.setImageUrl(fileService.uploadSingle(newProduct.getFile()));
        productEntity.setCategory(categoryService.getById(categoryId).get());
        productRepository.save(productEntity);
    }

    @Override
    public int getCountStock(long id) {
        return productRepository.findById(id).get().getQuantity();
    }
}
