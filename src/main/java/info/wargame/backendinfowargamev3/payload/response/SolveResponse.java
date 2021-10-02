package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SolveResponse {
    private Integer rank;
    private Integer score;
    private int maxProblem;
    private int solveProblem;
    private Double solveStatistics;
}
