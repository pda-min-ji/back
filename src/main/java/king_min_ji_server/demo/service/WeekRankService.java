package king_min_ji_server.demo.service;

import king_min_ji_server.demo.converter.Week_rankingConverter;
import king_min_ji_server.demo.domain.Week_ranking;
import king_min_ji_server.demo.repository.WeekRankingRepository;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekRankService {

    private final WeekRankingRepository weekRankingRepository;

    public List<RankResponseDTO.Week_rankRes> getWeeklyRankings() {
        List<Week_ranking> weekRankings = weekRankingRepository.findAll();
        return Week_rankingConverter.toWeekRankList(weekRankings);
    }
}
