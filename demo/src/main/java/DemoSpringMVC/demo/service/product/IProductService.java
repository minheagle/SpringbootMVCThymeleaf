package DemoSpringMVC.demo.service.product;

import DemoSpringMVC.demo.domain.product.CreateProduct;
import DemoSpringMVC.demo.domain.product.Product;
import DemoSpringMVC.demo.entity.ProductEntity;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<Product> getAll();
    List<Product> getAllByCategoryId(long categoryId);
    Product getById(long id);
    Product getByProductSlug(String slug);
    void create(CreateProduct newProduct, long categoryId) throws IOException;
    int getCountStock(long id);
}
