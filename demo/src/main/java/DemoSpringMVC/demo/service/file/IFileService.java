package DemoSpringMVC.demo.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileService {
    String uploadSingle(MultipartFile file) throws IOException;
    List<String> uploadMultiple(List<MultipartFile> files) throws IOException;
}
