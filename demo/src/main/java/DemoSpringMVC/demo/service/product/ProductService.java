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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllByCategoryId(long categoryId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(long id) {
        return modelMapper.map(productRepository.findById(id).get(), Product.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByProductSlug(String slug) {
        ProductEntity product = productRepository.findBySlug(slug).get();
        return modelMapper.map(product, Product.class);
    }

    @Override
    @Transactional
    public void create(CreateProduct newProduct, long categoryId) throws IOException {
        ProductEntity productEntity = modelMapper.map(newProduct, ProductEntity.class);
        productEntity.setImageUrl(fileService.uploadSingle(newProduct.getFile()));
        productEntity.setCategory(categoryService.getById(categoryId).get());
        productRepository.save(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCountStock(long id) {
        return productRepository.findById(id).get().getQuantity();
    }
}
