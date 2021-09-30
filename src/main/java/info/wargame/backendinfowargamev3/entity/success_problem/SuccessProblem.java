package info.wargame.backendinfowargamev3.entity.success_problem;

import info.wargame.backendinfowargamev3.entity.problem.Problem;
import info.wargame.backendinfowargamev3.entity.user.User;
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
public class SuccessProblem {
    @Id
    private String email;

    @ManyToOne
    @JoinColumn(name = "email")
    @MapsId
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;
}
