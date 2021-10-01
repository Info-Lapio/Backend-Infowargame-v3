package info.wargame.backendinfowargamev3.payload.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeResponse {
    private Long noticeId;
    private String title;
    private String content;
    private LocalDateTime writeAt;
    private String writer;
}
