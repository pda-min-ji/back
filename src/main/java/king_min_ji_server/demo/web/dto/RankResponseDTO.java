package king_min_ji_server.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class RankResponseDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Week_rankListRes {
        List<RankResponseDTO.Week_rankRes> weekRankList;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Week_rankRes{
        String name;
        Integer ranking;
        Long userId;
        Integer point;
        String imgPath;
        String bojId;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Total_rankListRes {
        List<RankResponseDTO.Total_rankListRes> weekRankList;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Total_rankRes{
        String name;
        Integer ranking;
        Long userId;
        Integer point;
        String imgPath;
        String bojId;
    }
}
