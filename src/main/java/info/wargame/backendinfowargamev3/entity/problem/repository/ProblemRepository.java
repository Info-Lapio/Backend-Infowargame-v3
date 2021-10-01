package info.wargame.backendinfowargamev3.entity.problem.repository;

import info.wargame.backendinfowargamev3.entity.problem.Problem;
import info.wargame.backendinfowargamev3.payload.response.ProblemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findByProblemId(Long problemId);

    @Query("select p.problemId, p.title, p.content, p.problemType, p.score, pf.fileName from Problem p join ProblemFile pf on p.problemId = pf.problemId")
    Page<ProblemResponse> getProblems(Pageable pageable);

    void deleteByProblemId(Long problemId);
}
