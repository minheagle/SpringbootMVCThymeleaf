package DemoSpringMVC.demo.util.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ContentTypeValidator implements ConstraintValidator<ContentType, MultipartFile> {
    private String[] allowedTypes;

    @Override
    public void initialize(ContentType constraintAnnotation) {
        this.allowedTypes = constraintAnnotation.allowedTypes();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file.isEmpty()) {
            return true;
        }
        for (String allowedType : allowedTypes) {
            if (file.getContentType().equalsIgnoreCase(allowedType)) {
                return true;
            }
        }
        return false;
    }
}
