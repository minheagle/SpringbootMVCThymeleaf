package DemoSpringMVC.demo.service.category;

import DemoSpringMVC.demo.entity.CategoryEntity;
import DemoSpringMVC.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryEntity> getAllChildFromParent(CategoryEntity parentCategory) {
        return categoryRepository.findAllByParentCategory(parentCategory.getLeft(), parentCategory.getRight());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryEntity> getById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryEntity> getAllLeaf() {
        return categoryRepository.findAllLeaf();
    }

    @Override
    @Transactional
    public CategoryEntity create(CategoryEntity parentCategory, CategoryEntity newCategory) {
        newCategory.setLeft(parentCategory.getRight());
        newCategory.setRight(parentCategory.getRight() + 1);
        categoryRepository.updateRight(parentCategory.getRight(), 2);
        categoryRepository.updateLeft(parentCategory.getRight(), 2);
        categoryRepository.save(newCategory);
        return null;
    }

    @Override
    public CategoryEntity update(long id, CategoryEntity updateCategory) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        CategoryEntity category = categoryRepository.findById(id).get();
        int space = -(category.getRight() - category.getLeft() + 1);
        categoryRepository.updateLeft(category.getRight(), space);
        categoryRepository.updateRight(category.getRight(), space);

        categoryRepository.deleteById(id);
    }
}
