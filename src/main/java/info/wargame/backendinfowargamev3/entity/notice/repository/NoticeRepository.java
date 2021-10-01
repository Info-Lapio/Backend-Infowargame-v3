package info.wargame.backendinfowargamev3.entity.notice.repository;

import info.wargame.backendinfowargamev3.entity.notice.Notice;
import info.wargame.backendinfowargamev3.payload.response.NoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("select n.noticeId, n.title, n.content, n.noticeAt, n.writer from Notice n")
    Page<NoticeResponse> getNotice(Pageable pageable);

    Optional<Notice> findByNoticeId(Long noticeId);

    void deleteByNoticeId(Long noticeId);
}
