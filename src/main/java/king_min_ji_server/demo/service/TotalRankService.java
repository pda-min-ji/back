package king_min_ji_server.demo.service;

import jakarta.transaction.Transactional;
import king_min_ji_server.demo.converter.Total_rankingConverter;
import king_min_ji_server.demo.domain.Total_ranking;
import king_min_ji_server.demo.repository.TotalRankingRepository;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalRankService {

    private final TotalRankingRepository totalRankingRepository;

    public List<RankResponseDTO.Total_rankRes> getTotalRankings() {
        List<Total_ranking> totalRankings = totalRankingRepository.findAll();
        return Total_rankingConverter.toTotalRankList(totalRankings);
    }

    @Transactional
    public void updateTotalRankingRanks() {
        List<Total_ranking> allRankings = totalRankingRepository.findAll();

        // 포인트 내림차순, 한글 이름 오름차순으로 정렬
        allRankings.sort(Comparator.comparingInt(Total_ranking::getPoint).reversed()
                .thenComparing(Total_ranking::getName));

        int currentRank = 1;
        int previousPoint = -1;
        int count = 0;

        for (Total_ranking ranking : allRankings) {
            count++;
            if (ranking.getPoint() != previousPoint) {
                currentRank = count;
                previousPoint = ranking.getPoint();
            }
            ranking.updateRank(currentRank); // Total_ranking 엔티티에 setRank() 메서드가 있다고 가정
            totalRankingRepository.save(ranking); // 변경 사항 저장
        }
    }
}
