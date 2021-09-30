package info.wargame.backendinfowargamev3.entity.success_problem.repository;

import info.wargame.backendinfowargamev3.entity.success_problem.SuccessProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessProblemRepository extends JpaRepository<SuccessProblem, Long> {
}
