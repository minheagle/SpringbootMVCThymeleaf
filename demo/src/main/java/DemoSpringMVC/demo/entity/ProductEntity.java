package DemoSpringMVC.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true)
    @NotBlank(message = "Tên của sản phẩm không được để trống !")
    @Size(max = 255, message = "Tên của sản phẩm không được quá 255 kí tự !")
    private String name;

    @Column(name = "slug", unique = true)
    @NotBlank(message = "Slug không được để trống !")
    @Size(max = 255, message = "Slug không được quá 255 kí tự !")
    private String slug;

    @Column(name = "price")
    @Min(value = 1, message = "Giá của sản phẩm phải lớn hơn 0 !")
    private double price = 0;

    @Column(name = "quantity")
    @Min(value = 1, message = "Số lượng hàng tồn kho phải lớn hơn 0 !")
    private int quantity = 1;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sold")
    private int sold = 0;

    @Column(name = "rating")
    private float rating = 0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                ", sold=" + sold +
                ", rating=" + rating +
                ", category=" + category +
                '}';
    }
}
