package info.wargame.backendinfowargamev3.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class FileValidate {

    @Value("${file.dir}")
    private String fileDir;

    @Value(("${image.dir}"))
    private String imageDir;

    @SneakyThrows
    public String validateImageAndSave(MultipartFile image) {
        if(image.isEmpty())
            throw new RuntimeException("Image bad request");

        String originName = image.getOriginalFilename();

        if(originName == null || originName.isEmpty())
            throw new RuntimeException("Image name bad request");

        String ex = StringUtils.getFilenameExtension(originName);
        if(ex == null || ex.isEmpty())
            throw new RuntimeException("Ex bad request");

        if(!(ex.contains("jpg") || ex.contains("png") || ex.contains("jpeg")))
            throw new RuntimeException("Ex bad request");

        String fileName = UUID.randomUUID() + "." + ex;
        image.transferTo(new File(imageDir, fileName));

        return fileName;
    }
}
