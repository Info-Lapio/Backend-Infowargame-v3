package info.wargame.backendinfowargamev3.payload.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
public class WriteEventRequest {
    private String title;
    private String content;
    private List<MultipartFile> images;
}
