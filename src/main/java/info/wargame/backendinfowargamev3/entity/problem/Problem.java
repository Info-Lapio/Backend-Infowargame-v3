package info.wargame.backendinfowargamev3.entity.problem;

import info.wargame.backendinfowargamev3.entity.problem.enums.ProblemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long problemId;

    private String title;

    @Column(length = 1000)
    private String content;

    private String frag;

    private Integer score;

    private ProblemType problemType;

    private Boolean existFile;

    public Problem updateProblem(String title, String content, String frag, Integer score, ProblemType problemType) {
        this.title = title;
        this.content = content;
        this.frag = frag;
        this.score = score;
        this.problemType = problemType;

        return this;
    }
}
