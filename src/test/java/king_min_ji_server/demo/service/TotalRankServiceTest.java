package king_min_ji_server.demo.service;

import king_min_ji_server.demo.domain.Total_ranking;
import king_min_ji_server.demo.repository.TotalRankingRepository;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TotalRankServiceTest {

    @Mock
    private TotalRankingRepository totalRankingRepository;

    private TotalRankService totalRankService;

    private List<Total_ranking> mockTotalRankEntities;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        totalRankService = new TotalRankService(totalRankingRepository);

        mockTotalRankEntities = Arrays.asList(
                Total_ranking.builder().name("Alice").ranking(1).userId(1L).point(200).imgPath("/img/alice.png").bojId("Alice123").build(),
                Total_ranking.builder().name("Bob").ranking(2).userId(2L).point(180).imgPath("/img/bob.png").bojId("Bob123").build()
        );
    }

    @Test
    void testGetTotalRankings_withData() {
        when(totalRankingRepository.findAll()).thenReturn(mockTotalRankEntities);

        List<RankResponseDTO.Total_rankRes> result = totalRankService.getTotalRankings();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Alice");
        assertThat(result.get(1).getName()).isEqualTo("Bob");
    }
}
