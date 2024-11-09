package king_min_ji_server.demo.repository;

import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.domain.mapping.User_Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserQuestionRepository extends JpaRepository<User_Question, Long> {
    Optional<User_Question> findByUserAndQuestion(User user, Question question);


    @Query(value = "SELECT u.boj_id, SUM(q.level) AS total_level " +
            "FROM user_question uq " +
            "JOIN question q ON uq.question_id = q.id " +
            "JOIN user u ON uq.user_id = u.id " +
            "GROUP BY u.boj_id " +
            "ORDER BY total_level DESC",
            nativeQuery = true)
    List<Object[]> findSumOfAllUsersQuestionLevels();
}
