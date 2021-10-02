package info.wargame.backendinfowargamev3.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class NodeResponse {
    private LocalDate date;
    private int solve;
}
