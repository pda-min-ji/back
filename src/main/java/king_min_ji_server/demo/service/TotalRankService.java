package king_min_ji_server.demo.service;

import king_min_ji_server.demo.converter.Total_rankingConverter;
import king_min_ji_server.demo.domain.Total_ranking;
import king_min_ji_server.demo.repository.TotalRankingRepository;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalRankService {

    private final TotalRankingRepository totalRankingRepository;

    public List<RankResponseDTO.Total_rankRes> getTotalRankings() {
        List<Total_ranking> totalRankings = totalRankingRepository.findAll();
        return Total_rankingConverter.toTotalRankList(totalRankings);
    }
}
