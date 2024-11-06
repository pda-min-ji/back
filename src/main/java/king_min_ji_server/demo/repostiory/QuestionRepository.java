package king_min_ji_server.demo.repostiory;

import king_min_ji_server.demo.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByNumber(String number);
}
