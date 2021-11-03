package info.wargame.backendinfowargamev3.service.user;

import info.wargame.backendinfowargamev3.entity.problem.repository.ProblemRepository;
import info.wargame.backendinfowargamev3.entity.success_problem.repository.SuccessProblemRepository;
import info.wargame.backendinfowargamev3.entity.user.User;
import info.wargame.backendinfowargamev3.entity.user.enums.UserAuthority;
import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.error.exceptions.UserNotFoundException;
import info.wargame.backendinfowargamev3.payload.request.SignUpRequest;
import info.wargame.backendinfowargamev3.payload.request.UpdateUserInfoRequest;
import info.wargame.backendinfowargamev3.payload.response.NodeResponse;
import info.wargame.backendinfowargamev3.payload.response.SolveResponse;
import info.wargame.backendinfowargamev3.payload.response.UserResponse;
import info.wargame.backendinfowargamev3.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final SuccessProblemRepository successProblemRepository;

    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;

    private UserResponse setUserResponse(User user) {
        int maxProblemNum = problemRepository.countAll();
        int successNum = successProblemRepository.countByUser_Email(user.getEmail());

        LocalDate start = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalDate end = start.minusDays(7);

        List<NodeResponse> nodeResponses = new ArrayList<>();

        for(int i = 0; i <= 7; i++) {
            LocalDate date = end.plusDays(i);
            int solveNum = successProblemRepository.countByUser_EmailAndSolveAt(user.getEmail(), end);
            nodeResponses.add(
                    NodeResponse.builder()
                            .date(date)
                            .solve(solveNum)
                            .build()
            );
        }

        int rank = userRepository.countByScoreGreaterThan(user.getScore());

        return UserResponse.builder()
                .name(user.getNickName())
                .nodeResponses(nodeResponses)
                .solveResponse(
                        SolveResponse.builder()
                                .maxProblem(maxProblemNum)
                                .rank(rank)
                                .score(user.getScore())
                                .solveStatistics(successNum == 0 ? 0.0 : successNum /maxProblemNum * 100)
                                .solveProblem(successNum)
                                .build()
                )
                .email(user.getEmail())
                .build();
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        userRepository.save(
                User.builder()
                        .email(signUpRequest.getEmail())
                        .nickName(signUpRequest.getNickName())
                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .score(0)
                        .userAuthority(UserAuthority.USER)
                        .build()
        );
    }

    @Override
    public UserResponse getMyInfo() {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        return setUserResponse(user);
    }

    @Override
    public UserResponse getUserInfo(String email) {
        userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        User target = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        return setUserResponse(target);
    }

    @Override
    public void updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        userRepository.save(
                user.updateName(updateUserInfoRequest.getName())
        );
    }
}
