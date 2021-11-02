package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.payload.response.UserRankResponse;
import info.wargame.backendinfowargamev3.service.rank.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rank/v1")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @GetMapping("/{pageNum}")
    public UserRankResponse getRank(@PathVariable int pageNum) {
        return rankService.getRank(pageNum);
    }
}
