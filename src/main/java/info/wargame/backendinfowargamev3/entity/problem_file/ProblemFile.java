package info.wargame.backendinfowargamev3.entity.problem_file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProblemFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long problemId;

    private String fileName;

    public ProblemFile updateFile(String fileName) {
        this.fileName = fileName;

        return this;
    }
}
