package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserRankResponse {
    private List<RankerResponse> rankerResponses;
    private TopThreeResponse topThreeResponse;
}
