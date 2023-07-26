package DemoSpringMVC.demo.domain.product;

import DemoSpringMVC.demo.entity.CategoryEntity;
import DemoSpringMVC.demo.util.customAnnotation.ContentType;
import DemoSpringMVC.demo.util.customAnnotation.FileSize;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {
    @NotBlank(message = "Tên của sản phẩm không được để trống !")
    @Size(max = 255, message = "Tên của sản phẩm không được quá 255 kí tự !")
    private String name;

    @NotBlank(message = "Slug không được để trống !")
    @Size(max = 255, message = "Slug không được quá 255 kí tự !")
    private String slug;

    @Min(value = 1, message = "Giá của sản phẩm phải lớn hơn 0 !")
    private double price = 0;

    @Min(value = 1, message = "Số lượng hàng tồn kho phải lớn hơn 0 !")
    private int quantity = 1;

    @FileSize(maxSize = 5 * 1024 * 1024)
    @ContentType
    private MultipartFile file;

    @Override
    public String toString() {
        return "CreateProduct{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", file=" + file.getSize() +
                '}';
    }
}
