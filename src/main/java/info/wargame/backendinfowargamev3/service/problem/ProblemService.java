package info.wargame.backendinfowargamev3.service.problem;

import info.wargame.backendinfowargamev3.payload.request.UpdateProblemRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteProblemRequest;
import info.wargame.backendinfowargamev3.payload.response.ProblemResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProblemService {
    void writeProblem(WriteProblemRequest writeProblemRequest);
    void submitProblem(Long problemId, String frag);
    ResponseEntity<Object> downloadProblemFile(String fileName);
    List<ProblemResponse> readProblem(int pageNum);
    void updateProblem(Long problemId, UpdateProblemRequest updateProblemRequest);
    void deleteProblem(Long problemId);
}
