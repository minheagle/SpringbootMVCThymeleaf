package DemoSpringMVC.demo.domain.product;

import DemoSpringMVC.demo.entity.CategoryEntity;
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
public class Product {
    private long id;
    private String name;
    private String slug;
    private double price;
    private int quantity;
    private String imageUrl;
    private float rating;
    private CategoryEntity category;
}
