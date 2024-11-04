package king_min_ji_server.demo.domain;


import jakarta.persistence.*;
import king_min_ji_server.demo.domain.common.BaseEntity;
import king_min_ji_server.demo.domain.mapping.User_Question;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String number;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 30)
    private String level;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<User_Question> userLanguageList = new ArrayList<>();

}