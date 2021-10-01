package info.wargame.backendinfowargamev3.service.problem;

import info.wargame.backendinfowargamev3.entity.problem.Problem;
import info.wargame.backendinfowargamev3.entity.problem.repository.ProblemRepository;
import info.wargame.backendinfowargamev3.entity.problem_file.ProblemFile;
import info.wargame.backendinfowargamev3.entity.problem_file.repository.ProblemFileRepository;
import info.wargame.backendinfowargamev3.entity.success_problem.SuccessProblem;
import info.wargame.backendinfowargamev3.entity.success_problem.repository.SuccessProblemRepository;
import info.wargame.backendinfowargamev3.entity.user.User;
import info.wargame.backendinfowargamev3.entity.user.enums.UserAuthority;
import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.payload.request.UpdateProblemRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteProblemRequest;
import info.wargame.backendinfowargamev3.payload.response.ProblemResponse;
import info.wargame.backendinfowargamev3.security.auth.AuthenticationFacade;
import info.wargame.backendinfowargamev3.utils.FileValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final ProblemFileRepository problemFileRepository;
    private final SuccessProblemRepository successProblemRepository;

    private final AuthenticationFacade authenticationFacade;
    private final FileValidate fileValidate;

    @Value("${file.dir}")
    private String fileDir;

    private final int PAGE_NUM = 20;

    @Override
    public void writeProblem(WriteProblemRequest writeProblemRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Problem problem = problemRepository.save(
                Problem.builder()
                        .title(writeProblemRequest.getTitle())
                        .content(writeProblemRequest.getContent())
                        .frag(writeProblemRequest.getFrag())
                        .score(writeProblemRequest.getScore())
                        .existFile(!writeProblemRequest.getFiles().isEmpty())
                        .build()
        );

        if(!writeProblemRequest.getFiles().isEmpty()) {
            problemFileRepository.save(
                    ProblemFile.builder()
                            .problemId(problem.getProblemId())
                            .fileName(fileValidate.validateFileAndSave(writeProblemRequest.getFiles()))
                            .build()
            );
        }
    }

    @Override
    public void submitProblem(Long problemId, String frag) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Problem problem = problemRepository.findByProblemId(problemId)
                .orElseThrow(RuntimeException::new);

        if(!problem.getFrag().equals(frag))
            throw new RuntimeException("Fail frag");

        successProblemRepository.save(
                SuccessProblem.builder()
                        .problem(problem)
                        .user(user)
                        .build()
        );

        userRepository.save(
                user.scoreUp(problem.getScore())
        );
    }

    @Override
    public ResponseEntity<Object> downloadProblemFile(String fileName) {
        File file = new File(fileDir, fileName);
        if(!file.exists())
            throw new RuntimeException("File not found");

        try {
            Resource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<ProblemResponse> readProblem(int pageNum) {
        userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        Page<ProblemResponse> problemResponses = problemRepository.getProblems(PageRequest.of(pageNum, PAGE_NUM));

        return problemResponses.toList();
    }

    @Override
    public void updateProblem(Long problemId, UpdateProblemRequest updateProblemRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Problem problem = problemRepository.findByProblemId(problemId)
                .orElseThrow(RuntimeException::new);

        problemRepository.save(
                problem.updateProblem(
                        updateProblemRequest.getTitle(),
                        updateProblemRequest.getContent(),
                        updateProblemRequest.getFrag(),
                        updateProblemRequest.getScore(),
                        updateProblemRequest.getProblemType()
                )
        );

        if(problem.getExistFile()) {
            ProblemFile problemFile = problemFileRepository.findByProblemId(problemId)
                    .orElseThrow(RuntimeException::new);

            File file = new File(fileDir, problemFile.getFileName());
            if(!file.exists())
                throw new RuntimeException("file not found");

            file.delete();

            problemFileRepository.save(
                    problemFile.updateFile(fileValidate.validateFileAndSave(updateProblemRequest.getFiles()))
            );
        }
    }

    @Override
    @Transactional
    public void deleteProblem(Long problemId) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Problem problem = problemRepository.findByProblemId(problemId)
                .orElseThrow(RuntimeException::new);

        problemRepository.deleteByProblemId(problemId);

        if(problem.getExistFile()) {
            ProblemFile problemFile = problemFileRepository.findByProblemId(problemId)
                    .orElseThrow(RuntimeException::new);

            File file = new File(fileDir, problemFile.getFileName());
            if(!file.exists())
                throw new RuntimeException("file not found");
            file.delete();

            problemFileRepository.deleteByProblemId(problemId);
        }
    }
}
