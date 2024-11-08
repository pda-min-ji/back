package king_min_ji_server.demo.converter;

import king_min_ji_server.demo.domain.Total_ranking;
import king_min_ji_server.demo.web.dto.RankResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Total_rankingConverter {

    public static List<RankResponseDTO.Total_rankRes> toTotalRankList(List<Total_ranking> totalRankings) {
        return totalRankings.stream().map(totalRanking -> RankResponseDTO.Total_rankRes.builder()
                        .name(totalRanking.getName())
                        .ranking(totalRanking.getRanking())
                        .userId(totalRanking.getUserId())
                        .point(totalRanking.getPoint())
                        .imgPath(totalRanking.getImgPath())
                        .bojId(totalRanking.getBojId())
                        .build())
                .collect(Collectors.toList());
    }
}
