package info.wargame.backendinfowargamev3.service.notice;

import info.wargame.backendinfowargamev3.entity.notice.Notice;
import info.wargame.backendinfowargamev3.entity.notice.repository.NoticeRepository;
import info.wargame.backendinfowargamev3.entity.user.User;
import info.wargame.backendinfowargamev3.entity.user.enums.UserAuthority;
import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.payload.request.UpdateNoticeRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteNoticeRequest;
import info.wargame.backendinfowargamev3.payload.response.NoticeResponse;
import info.wargame.backendinfowargamev3.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    private final AuthenticationFacade authenticationFacade;

    private final int PAGE_NUM = 6;

    @Override
    public void writeNotice(WriteNoticeRequest writeNoticeRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        noticeRepository.save(
                Notice.builder()
                        .noticeAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                        .title(writeNoticeRequest.getTitle())
                        .content(writeNoticeRequest.getContent())
                        .writer(user.getNickName())
                        .build()
        );
    }

    @Override
    public List<NoticeResponse> mainNotices() {
        userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        Page<NoticeResponse> noticeResponses = noticeRepository.getNotice(PageRequest.of(0, PAGE_NUM));

        return noticeResponses.toList();
    }

    @Override
    public List<NoticeResponse> readNotices(int pageNum) {
        userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        Page<NoticeResponse> noticeResponses = noticeRepository.getNotice(PageRequest.of(pageNum, PAGE_NUM));

        return noticeResponses.toList();
    }

    @Override
    public void updateNotice(Long noticeId, UpdateNoticeRequest updateNoticeRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(RuntimeException::new);

        noticeRepository.save(
                notice.updateNotice(updateNoticeRequest.getTitle(), updateNoticeRequest.getContent(), user.getNickName())
        );
    }

    @Override
    @Transactional
    public void deleteNotice(Long noticeId) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(RuntimeException::new);

        noticeRepository.deleteByNoticeId(noticeId);
    }
}
