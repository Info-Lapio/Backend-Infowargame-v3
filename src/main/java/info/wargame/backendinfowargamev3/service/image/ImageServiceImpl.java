package info.wargame.backendinfowargamev3.service.image;

import info.wargame.backendinfowargamev3.entity.event_image.EventImage;
import info.wargame.backendinfowargamev3.entity.event_image.repository.EventImageRepository;
import info.wargame.backendinfowargamev3.error.exceptions.EventImageNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final EventImageRepository eventImageRepository;

    @Value("${image.dir}")
    private String imageDir;

    @SneakyThrows
    @Override
    public byte[] getImage(String imageName) {
        EventImage eventImage = eventImageRepository.findByImageName(imageName)
                .orElseThrow(EventImageNotFoundException::new);

        File file = new File(imageDir, eventImage.getImageName());
        if(!file.exists())
            throw new EventImageNotFoundException();

        return IOUtils.toByteArray(new FileInputStream(file));
    }
}
