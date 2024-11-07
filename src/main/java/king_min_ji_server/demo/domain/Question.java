package king_min_ji_server.demo.domain;


import jakarta.persistence.*;
import king_min_ji_server.demo.domain.common.BaseEntity;
import king_min_ji_server.demo.domain.mapping.Question_Tag;
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

    @Column(nullable = false, length = 255)
    private String url;

    @Column(nullable = false, length = 30)
    private int number;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 30)
    private int level;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question_Tag> questionTags = new ArrayList<>();


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User_Question> userLanguageList = new ArrayList<>();
}