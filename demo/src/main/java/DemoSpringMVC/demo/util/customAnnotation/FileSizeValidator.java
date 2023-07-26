package DemoSpringMVC.demo.util.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private long maxSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file.isEmpty()) {
            return true;
        }

        return file.getSize() <= maxSize;
    }
}
