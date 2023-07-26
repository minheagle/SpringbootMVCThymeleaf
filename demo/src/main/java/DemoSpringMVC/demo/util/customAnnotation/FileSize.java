package DemoSpringMVC.demo.util.customAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
    String message() default "Kích thước tệp vượt quá giới hạn cho phép.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long maxSize() default 1024 * 1024;
}
