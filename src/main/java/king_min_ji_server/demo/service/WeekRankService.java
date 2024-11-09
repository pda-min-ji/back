package king_min_ji_server.demo.service;

import jakarta.transaction.Transactional;
import king_min_ji_server.demo.converter.Week_rankingConverter;
import king_min_ji_server.demo.domain.Week_ranking;
import king_min_ji_server.demo.repository.WeekRankingRepository;
import king_min_ji_server.demo.web.dto.RankResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekRankService {

    private final WeekRankingRepository weekRankingRepository;

    public List<RankResponseDTO.Week_rankRes> getWeeklyRankings() {
        List<Week_ranking> weekRankings = weekRankingRepository.findAll();
        return Week_rankingConverter.toWeekRankList(weekRankings);
    }

    @Transactional
    public void updateWeekRankingRanks() {
        List<Week_ranking> allRankings = weekRankingRepository.findAll();

        // 포인트 내림차순, 한글 이름 오름차순으로 정렬
        allRankings.sort(Comparator.comparingInt(Week_ranking::getPoint).reversed()
                .thenComparing(Week_ranking::getName));

        int currentRank = 1;
        int previousPoint = -1;
        int count = 0; // 동일한 점수에 대해 순위 동률 처리를 위한 변수

        for (Week_ranking ranking : allRankings) {
            count++;
            if (ranking.getPoint() != previousPoint) {
                currentRank = count;
                previousPoint = ranking.getPoint();
            }
            ranking.updateRank(currentRank); // Week_ranking 엔티티에 setRank() 메서드가 있다고 가정
            weekRankingRepository.save(ranking); // 변경 사항 저장
        }
    }
}
