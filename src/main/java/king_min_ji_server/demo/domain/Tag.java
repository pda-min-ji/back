package king_min_ji_server.demo.domain;

import jakarta.persistence.*;
import king_min_ji_server.demo.domain.mapping.Question_Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String tag;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<Question_Tag> questionTags = new ArrayList<>();
}
