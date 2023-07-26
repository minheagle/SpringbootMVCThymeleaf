package DemoSpringMVC.demo.service.cart;

import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;
import DemoSpringMVC.demo.domain.product.Product;

import java.util.List;

public interface ICartService {
    List<OrderDetail> getAll();
    void addItem(Product product);

    void updateQuantity(long productId, int quantity);
    void removeItem(int id);
    void emptyCart();
    int count();
    double getTotalCost();
}
