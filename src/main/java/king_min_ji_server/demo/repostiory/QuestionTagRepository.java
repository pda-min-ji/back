package king_min_ji_server.demo.repostiory;

import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.mapping.Question_Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionTagRepository extends JpaRepository<Question_Tag, Long> {

    List<Question_Tag> findByQuestion(Question question);
}
