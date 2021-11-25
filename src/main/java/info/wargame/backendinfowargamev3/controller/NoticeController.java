package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.payload.request.UpdateNoticeRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteNoticeRequest;
import info.wargame.backendinfowargamev3.payload.response.NoticeResponse;
import info.wargame.backendinfowargamev3.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/notice/v1")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public List<NoticeResponse> mainNotices() {
        return noticeService.mainNotices();
    }

    @GetMapping("/{pageNum}")
    public List<NoticeResponse> readNotices(@PathVariable int pageNum) {
        return noticeService.readNotices(pageNum);
    }

    @PostMapping
    public void writeNotice(@RequestBody WriteNoticeRequest writeNoticeRequest) {
        noticeService.writeNotice(writeNoticeRequest);
    }

    @PutMapping("/{noticeId}")
    public void updateNotice(@PathVariable Long noticeId,
                             @RequestBody UpdateNoticeRequest updateNoticeRequest) {
        noticeService.updateNotice(noticeId, updateNoticeRequest);
    }

    @DeleteMapping("/{noticeId}")
    public void deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);
    }
}
