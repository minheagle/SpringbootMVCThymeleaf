package DemoSpringMVC.demo.domain.cart;

import jakarta.validation.constraints.NotBlank;
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
public class Checkout {
    @NotBlank(message = "Không được để trống !")
    private String customerName;

    @NotBlank(message = "Không được để trống !")
    private String customerAddress;
}
