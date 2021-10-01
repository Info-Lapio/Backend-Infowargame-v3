package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EventResponse {
    private Long eventId;
    private String eventName;
    private String content;
    private List<String> images;
}
