package info.wargame.backendinfowargamev3.entity.ban_user.repository;

import info.wargame.backendinfowargamev3.entity.ban_user.BanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanUserRepository extends JpaRepository<BanUser, String> {
}
