package info.wargame.backendinfowargamev3.entity.problem_file.repository;

import info.wargame.backendinfowargamev3.entity.problem_file.ProblemFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemFileRepository extends JpaRepository<ProblemFile, Long> {
    Optional<ProblemFile> findByProblemId(Long problemId);
    void deleteByProblemId(Long problemId);
}
