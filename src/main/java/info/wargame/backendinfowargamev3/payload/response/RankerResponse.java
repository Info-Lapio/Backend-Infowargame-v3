package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankerResponse {
    private String name;
    private String eamil;
    private Integer score;
    private Boolean isMine;
}
