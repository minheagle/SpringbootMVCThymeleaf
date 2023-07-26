package DemoSpringMVC.demo.repository;

import DemoSpringMVC.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findBySlug(String slug);
}
