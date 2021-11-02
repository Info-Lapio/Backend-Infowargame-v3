package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TopThreeResponse {
    private List<NodeResponse> firstRanker;
    private List<NodeResponse> secondReanker;
    private List<NodeResponse> thridRanker;
}
