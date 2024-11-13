package king_min_ji_server.demo.repository;

import king_min_ji_server.demo.domain.Total_ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TotalRankingRepository extends JpaRepository<Total_ranking, Long> {
    Optional<Total_ranking> findByBojId(String bojId);
    @Query("select tr from Total_ranking tr order by tr.ranking")
    List<Total_ranking> findAllBySort();


}
