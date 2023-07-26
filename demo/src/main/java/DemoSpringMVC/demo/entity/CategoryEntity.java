package DemoSpringMVC.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    @NotBlank(message = "Nội dung của danh mục không được để trống !")
    private String content;

    @Column(name = "left_position")
    private int left;

    @Column(name = "right_position")
    private int right;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
