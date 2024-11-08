package king_min_ji_server.demo.repostiory;

import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.domain.mapping.User_Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuestionRepository extends JpaRepository<User_Question, Long> {
    Optional<User_Question> findByUserAndQuestion(User user, Question question);
}
