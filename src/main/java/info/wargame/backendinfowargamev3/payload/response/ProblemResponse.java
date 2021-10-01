package info.wargame.backendinfowargamev3.payload.response;

import info.wargame.backendinfowargamev3.entity.problem.enums.ProblemType;
import lombok.Getter;

@Getter
public class ProblemResponse {
    private Long problemId;
    private String title;
    private String content;
    private Integer score;
    private ProblemType problemType;
    private String fileName;
}
