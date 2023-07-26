package DemoSpringMVC.demo.service.file;

import DemoSpringMVC.demo.config.CloudinaryConfiguration;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileService implements IFileService{
    @Autowired
    private CloudinaryConfiguration cloudinaryConfiguration;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadSingle(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", cloudinaryConfiguration.getFolder()));

        String imageUrl = (String) uploadResult.get("secure_url");

        return imageUrl;
    }

    @Override
    public List<String> uploadMultiple(List<MultipartFile> files) throws IOException {
        return null;
    }
}
