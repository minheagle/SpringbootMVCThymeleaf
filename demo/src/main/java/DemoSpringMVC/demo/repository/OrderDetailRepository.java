package DemoSpringMVC.demo.repository;

import DemoSpringMVC.demo.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findAllByOrderId(long orderId);
}
