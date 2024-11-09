package king_min_ji_server.demo.repository;

import king_min_ji_server.demo.domain.Week_ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeekRankingRepository extends JpaRepository<Week_ranking, Long> {
    Optional<Week_ranking> findByBojId(String bojId);
}
