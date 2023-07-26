package DemoSpringMVC.demo.service.category;

import DemoSpringMVC.demo.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryEntity> getAll();
    List<CategoryEntity> getAllChildFromParent(CategoryEntity parentCategory);
    Optional<CategoryEntity> getById(long id);
    List<CategoryEntity> getAllLeaf();
    CategoryEntity create(CategoryEntity parentCategory ,CategoryEntity newCategory);
    CategoryEntity update(long id, CategoryEntity updateCategory);
    void delete(long id);
}
