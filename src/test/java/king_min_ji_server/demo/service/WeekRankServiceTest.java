package king_min_ji_server.demo.service;

import king_min_ji_server.demo.domain.Week_ranking;
import king_min_ji_server.demo.repository.WeekRankingRepository;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class WeekRankServiceTest {

    @Mock
    private WeekRankingRepository weekRankingRepository;

    private WeekRankService weekRankService;

    private List<Week_ranking> mockWeekRankEntities;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weekRankService = new WeekRankService(weekRankingRepository);

        mockWeekRankEntities = Arrays.asList(
                Week_ranking.builder().name("Alice").ranking(1).userId(1L).point(100).imgPath("/img/alice.png").bojId("Alice123").build(),
                Week_ranking.builder().name("Bob").ranking(2).userId(2L).point(90).imgPath("/img/bob.png").bojId("Bob123").build()
        );
    }

    @Test
    void testGetWeeklyRankings_withData() {
        when(weekRankingRepository.findAll()).thenReturn(mockWeekRankEntities);

        List<RankResponseDTO.Week_rankRes> result = weekRankService.getWeeklyRankings();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Alice");
        assertThat(result.get(1).getName()).isEqualTo("Bob");
    }
}
