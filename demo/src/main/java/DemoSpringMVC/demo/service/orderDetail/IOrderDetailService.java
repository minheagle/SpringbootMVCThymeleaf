package DemoSpringMVC.demo.service.orderDetail;

import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;
import DemoSpringMVC.demo.entity.OrderDetailEntity;
import DemoSpringMVC.demo.entity.OrderEntity;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getAll();
    List<OrderDetail> getAllByOrderId(long orderId);
    void create(OrderDetailEntity orderDetailEntity);
    void update(long id, OrderDetail orderDetail);
    void delete(long id);
}
