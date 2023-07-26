package DemoSpringMVC.demo.util.customAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContentTypeValidator.class)
public @interface ContentType {
    String message() default "Kiểu tệp không hợp lệ.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] allowedTypes() default {"image/jpeg", "image/jpg" , "image/png", "image/gif"};
}
