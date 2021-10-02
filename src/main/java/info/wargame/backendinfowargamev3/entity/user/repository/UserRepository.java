package info.wargame.backendinfowargamev3.entity.user.repository;

import info.wargame.backendinfowargamev3.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    int countByScoreGreaterThan(Integer score);
}
