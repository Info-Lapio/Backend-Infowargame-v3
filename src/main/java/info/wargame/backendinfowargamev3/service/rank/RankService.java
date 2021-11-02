package info.wargame.backendinfowargamev3.service.rank;

import info.wargame.backendinfowargamev3.payload.response.UserRankResponse;

public interface RankService {
    UserRankResponse getRank(int pageNum);
}
