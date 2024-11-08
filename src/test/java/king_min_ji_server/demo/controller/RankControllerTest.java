package king_min_ji_server.demo.controller;

import king_min_ji_server.demo.apiPayload.ApiResponse;
import king_min_ji_server.demo.domain.Total_ranking;
import king_min_ji_server.demo.domain.Week_ranking;
import king_min_ji_server.demo.service.TotalRankService;
import king_min_ji_server.demo.service.WeekRankService;
import king_min_ji_server.demo.web.controller.RankController;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class RankControllerTest {

    @Mock
    private WeekRankService weekRankService;

    @Mock
    private TotalRankService totalRankService;

    @InjectMocks
    private RankController rankController;

    private List<RankResponseDTO.Week_rankRes> mockWeekRanks;
    private List<RankResponseDTO.Total_rankRes> mockTotalRanks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockWeekRanks = Arrays.asList(
                new RankResponseDTO.Week_rankRes("Alice", 1, 1L, 100, "/img/alice.png", "Alice123"),
                new RankResponseDTO.Week_rankRes("Bob", 2, 2L, 90, "/img/bob.png", "Bob123")
        );

        mockTotalRanks = Arrays.asList(
                new RankResponseDTO.Total_rankRes("Alice", 1, 1L, 200, "/img/alice.png", "Alice123"),
                new RankResponseDTO.Total_rankRes("Bob", 2, 2L, 180, "/img/bob.png", "Bob123")
        );
    }

    @Test
    void testGetWeeklyRanking_withData() {
        when(weekRankService.getWeeklyRankings()).thenReturn(mockWeekRanks);

        ApiResponse<?> response = rankController.getWeeklyRanking();

        assertThat(response.getIsSuccess()).isTrue();
        assertThat(response.getResult()).isInstanceOf(List.class);
        assertThat(((List<?>) response.getResult()).size()).isEqualTo(2);
    }

    @Test
    void testGetTotalRanking_withData() {
        when(totalRankService.getTotalRankings()).thenReturn(mockTotalRanks);

        ApiResponse<?> response = rankController.getTotalRanking();

        assertThat(response.getIsSuccess()).isTrue();
        assertThat(response.getResult()).isInstanceOf(List.class);
        assertThat(((List<?>) response.getResult()).size()).isEqualTo(2);
    }
}
