package info.wargame.backendinfowargamev3.payload.request;

import lombok.Getter;

@Getter
public class WriteNoticeRequest {
    private String title;
    private String content;
}
