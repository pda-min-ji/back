package king_min_ji_server.demo.converter;

import king_min_ji_server.demo.domain.Week_ranking;
import king_min_ji_server.demo.web.dto.RankResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Week_rankingConverter {

    public static List<RankResponseDTO.Week_rankRes> toWeekRankList(List<Week_ranking> weekRankings) {
        return weekRankings.stream().map(weekRanking -> RankResponseDTO.Week_rankRes.builder()
                        .name(weekRanking.getName())
                        .ranking(weekRanking.getRanking())
                        .userId(weekRanking.getUserId())
                        .point(weekRanking.getPoint())
                        .imgPath(weekRanking.getImgPath())
                        .bojId(weekRanking.getBojId())
                        .build())
                .collect(Collectors.toList());
    }
}
