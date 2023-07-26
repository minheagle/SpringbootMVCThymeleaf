package DemoSpringMVC.demo.domain.customer;

import jakarta.validation.constraints.*;
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
public class CustomerRegister {
    @NotBlank(message = "Tên không được để trống !")
    @Size(max = 255, message = "Họ tên không được quá 255 kí tự !")
    private String name;

    @Min(value = 18, message = "Tuổi phải từ 18 tuổi trở lên !")
    @Max(value = 100, message = "Tuổi không được quá 100 !")
    private int age;

    @NotBlank(message = "Địa chỉ không được để trống !")
    @Size(max = 255, message = "Địa chỉ không được quá 255 kí tự !")
    private String address;

    @NotBlank(message = "Số điện thoại không để trống !")
    private String phone;

    @NotBlank(message = "Email không được để trống !")
    @Size(max = 255, message = "Email không được quá 255 kí tự !")
    @Email(message = "Email không hợp lệ !")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống !")
    @Size(min = 8, max = 25, message = "Mật khẩu chỉ từ 8 -> 25 kí tự !")
    private String password;

    @NotBlank(message = "Không được để trống !")
    @Size(min = 8, max = 25, message = "Mật khẩu chỉ từ 8 -> 25 kí tự !")
    private String rePassword;

    @Override
    public String toString() {
        return "CustomerRegister{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                '}';
    }
}
