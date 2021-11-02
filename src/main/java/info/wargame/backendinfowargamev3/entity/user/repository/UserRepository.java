package info.wargame.backendinfowargamev3.entity.user.repository;

import info.wargame.backendinfowargamev3.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);
    int countByScoreGreaterThan(Integer score);
}
