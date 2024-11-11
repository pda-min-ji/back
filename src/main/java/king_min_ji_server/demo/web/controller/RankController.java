package king_min_ji_server.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import king_min_ji_server.demo.apiPayload.ApiResponse;
import king_min_ji_server.demo.service.TotalRankService;
import king_min_ji_server.demo.service.WeekRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("/rank")
public class RankController {

    private final WeekRankService weekRankService;
    private final TotalRankService totalRankService;

    @GetMapping("/week")
    @Operation(summary = "주간 랭킹 조회", description = "주간 랭킹 데이터를 조회합니다.")
    public ApiResponse<?> getWeeklyRanking() {
        return ApiResponse.onSuccess(weekRankService.getWeeklyRankings());
    }

    @GetMapping("/total")
    @Operation(summary = "전체 랭킹 조회", description = "전체 랭킹 데이터를 조회합니다.")
    public ApiResponse<?> getTotalRanking() {
        return ApiResponse.onSuccess(totalRankService.getTotalRankings());
    }
}
