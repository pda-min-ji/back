package king_min_ji_server.demo.domain.mapping;

import jakarta.persistence.*;
import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.User;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User_Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
//
//    public void setUser(User user) {
//        if(this.user != null){
//            user.getUserLanguageList().remove(this);
//        }
//        this.user = user;
//        user.getUserLanguageList().add(this);
//    }
}
