package info.wargame.backendinfowargamev3.entity.success_problem;

import info.wargame.backendinfowargamev3.entity.problem.Problem;
import info.wargame.backendinfowargamev3.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SuccessProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rankId;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private LocalDate solveAt;
}
