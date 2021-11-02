package info.wargame.backendinfowargamev3.service.rank;

import info.wargame.backendinfowargamev3.entity.success_problem.repository.SuccessProblemRepository;
import info.wargame.backendinfowargamev3.entity.user.User;
import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.error.exceptions.UserNotFoundException;
import info.wargame.backendinfowargamev3.payload.response.NodeResponse;
import info.wargame.backendinfowargamev3.payload.response.RankerResponse;
import info.wargame.backendinfowargamev3.payload.response.TopThreeResponse;
import info.wargame.backendinfowargamev3.payload.response.UserRankResponse;
import info.wargame.backendinfowargamev3.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {

    private final UserRepository userRepository;
    private final SuccessProblemRepository successProblemRepository;

    private final AuthenticationFacade authenticationFacade;

    final int PAGE_SIZE = 50;
    final int MINUS_DAY = 10;

    @Override
    public UserRankResponse getRank(int pageNum) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        Page<User> ranker = userRepository
                .findAll(PageRequest.of(
                        pageNum,
                        PAGE_SIZE,
                        Sort.by(Sort.Direction.DESC, "score"))
                );

        List<RankerResponse> rankerResponses = new ArrayList<>();

        ranker.forEach(user1 -> {
            rankerResponses.add(
                    RankerResponse.builder()
                            .isMine(user == user1)
                            .eamil(user1.getEmail())
                            .score(user1.getScore())
                            .name(user1.getNickName())
                            .build()
            );
        });

        User first = ranker.getContent().get(0);
        User second = ranker.getContent().get(1);
        User third = ranker.getContent().get(2);

        List<NodeResponse> firstNodeResponse = new ArrayList<>();
        List<NodeResponse> secondNodeResponse = new ArrayList<>();
        List<NodeResponse> thirdNodeResponse = new ArrayList<>();

        LocalDate start = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalDate end = start.minusDays(MINUS_DAY);

        for(int i = 0; i <= MINUS_DAY; i++) {
            LocalDate date = end.plusDays(i);
            int firstSolve = successProblemRepository.countByUser_EmailAndSolveAt(first.getEmail(), date);
            int secondSolve = successProblemRepository.countByUser_EmailAndSolveAt(second.getEmail(), date);
            int thirdSolve = successProblemRepository.countByUser_EmailAndSolveAt(third.getEmail(), date);

            firstNodeResponse.add(
                    NodeResponse.builder()
                            .date(date)
                            .solve(firstSolve)
                            .build()
            );

            secondNodeResponse.add(
                    NodeResponse.builder()
                            .date(date)
                            .solve(secondSolve)
                            .build()
            );

            thirdNodeResponse.add(
                    NodeResponse.builder()
                            .date(date)
                            .solve(thirdSolve)
                            .build()
            );
        }

        return UserRankResponse.builder()
                .rankerResponses(rankerResponses)
                .topThreeResponse(
                        TopThreeResponse.builder()
                                .firstRanker(firstNodeResponse)
                                .secondReanker(secondNodeResponse)
                                .thridRanker(thirdNodeResponse)
                                .build()
                )
                .build();
    }
}
