package info.wargame.backendinfowargamev3.service.notice;

import info.wargame.backendinfowargamev3.payload.request.UpdateNoticeRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteNoticeRequest;
import info.wargame.backendinfowargamev3.payload.response.NoticeResponse;

import java.util.List;

public interface NoticeService {
    void writeNotice(WriteNoticeRequest writeNoticeRequest);
    List<NoticeResponse> mainNotices();
    List<NoticeResponse> readNotices(int pageNum);
    void updateNotice(Long noticeId, UpdateNoticeRequest updateNoticeRequest);
    void deleteNotice(Long noticeId);
}
