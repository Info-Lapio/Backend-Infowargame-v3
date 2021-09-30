package info.wargame.backendinfowargamev3.entity.notice.repository;

import info.wargame.backendinfowargamev3.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
