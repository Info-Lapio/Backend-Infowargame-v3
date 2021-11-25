package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.entity.problem.enums.ProblemType;
import info.wargame.backendinfowargamev3.payload.request.UpdateProblemRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteProblemRequest;
import info.wargame.backendinfowargamev3.payload.response.ProblemResponse;
import info.wargame.backendinfowargamev3.service.problem.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/{problemType}/{pageNum}")
    public List<ProblemResponse> getProblem(@PathVariable int pageNum,
                                            @PathVariable ProblemType problemType) {
        return problemService.readProblem(pageNum, problemType);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName) {
        return problemService.downloadProblemFile(fileName);
    }

    @PostMapping
    public void writeProblem(@RequestBody WriteProblemRequest writeProblemRequest) {
        problemService.writeProblem(writeProblemRequest);
    }

    @PostMapping("/submit/{problemId}")
    public void submitProblem(@PathVariable Long problemId,
                              @RequestParam String flag) {
        problemService.submitProblem(problemId, flag);
    }

    @PutMapping("/{problemId}")
    public void updateProblem(@PathVariable Long problemId,
                              @RequestBody UpdateProblemRequest updateProblemRequest) {
        problemService.updateProblem(problemId, updateProblemRequest);
    }

    @DeleteMapping("/{problemId}")
    public void deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
    }
}
