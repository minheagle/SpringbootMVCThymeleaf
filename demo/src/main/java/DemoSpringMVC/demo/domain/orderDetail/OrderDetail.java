package DemoSpringMVC.demo.domain.orderDetail;

import DemoSpringMVC.demo.domain.order.Order;
import DemoSpringMVC.demo.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private long id;
    private Order order;
    private Product product;
    private Integer quantity;
    private double price;

    public double getAmount(){
        return this.price * this.quantity;
    }
}
