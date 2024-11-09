package king_min_ji_server.demo.repository;

import king_min_ji_server.demo.domain.Total_ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TotalRankingRepository extends JpaRepository<Total_ranking, Long> {
    Optional<Total_ranking> findByBojId(String bojId);
}