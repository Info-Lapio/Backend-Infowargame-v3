package info.wargame.backendinfowargamev3.payload.request;

import info.wargame.backendinfowargamev3.entity.problem.enums.ProblemType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class UpdateProblemRequest {
    private String title;
    private String content;
    private String frag;
    private ProblemType problemType;
    private Integer score;
    private MultipartFile files;
}
