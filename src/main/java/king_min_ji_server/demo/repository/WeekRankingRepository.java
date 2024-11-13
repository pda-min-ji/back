package king_min_ji_server.demo.repository;
import king_min_ji_server.demo.domain.Total_ranking;

import king_min_ji_server.demo.domain.Week_ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WeekRankingRepository extends JpaRepository<Week_ranking, Long> {
    Optional<Week_ranking> findByBojId(String bojId);
    
    @Query("select wr from Week_ranking wr order by wr.ranking")
    List<Week_ranking> findAllBySort();
}
