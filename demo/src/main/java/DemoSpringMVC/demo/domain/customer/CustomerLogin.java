package DemoSpringMVC.demo.domain.customer;

import jakarta.persistence.Entity;
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
public class CustomerLogin {
    @NotBlank(message = "Email không được để trống !")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống !")
    private String password;
}
