package info.wargame.backendinfowargamev3.entity.problem.repository;

import info.wargame.backendinfowargamev3.entity.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
