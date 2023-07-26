package DemoSpringMVC.demo.domain.order;

import DemoSpringMVC.demo.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private Date orderDate;
    private String customerName;
    private String customerAddress;
}
