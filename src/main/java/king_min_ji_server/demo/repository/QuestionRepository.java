package king_min_ji_server.demo.repository;

import king_min_ji_server.demo.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByNumber(int number);
    @Query(value = "select * from question order by rand() limit 3", nativeQuery = true)
    List<Question> findByRandom3();
}

