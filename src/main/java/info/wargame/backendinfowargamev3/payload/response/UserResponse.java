package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {
    private String name;
    private String email;
    private SolveResponse solveResponse;
    private List<NodeResponse> nodeResponses;
}
