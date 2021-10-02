package info.wargame.backendinfowargamev3.entity.success_problem.repository;

import info.wargame.backendinfowargamev3.entity.success_problem.SuccessProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SuccessProblemRepository extends JpaRepository<SuccessProblem, Long> {
    int countByEmail(String email);
    List<SuccessProblem> findAllByEmail(String email);
    int countByEmailAndSolveAt(String email, LocalDate solveAt);
}
