package DemoSpringMVC.demo.domain.cart;

import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<OrderDetail> cartList = new ArrayList<>();
    public double getTotal(){
        return cartList.stream().map(item -> item.getAmount())
                .reduce(0.0, (a,b) -> a + b);
    }
}
