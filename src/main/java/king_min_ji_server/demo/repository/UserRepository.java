package king_min_ji_server.demo.repository;
import king_min_ji_server.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByBojId(String bojId); // 필드명과 정확히 일치하도록 수정
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.bojId = :bojId")
    Optional<User> findByBojId(@Param("bojId") String bojId);
}
