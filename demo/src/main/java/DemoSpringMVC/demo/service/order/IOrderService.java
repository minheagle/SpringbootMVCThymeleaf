package DemoSpringMVC.demo.service.order;

import DemoSpringMVC.demo.domain.cart.Checkout;
import DemoSpringMVC.demo.domain.order.Order;
import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order getById(long id);
    void create(Checkout checkout, List<OrderDetail> cartList);
}
