package king_min_ji_server.demo.domain;

import jakarta.persistence.*;
import king_min_ji_server.demo.domain.common.BaseEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Point extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long week_point;

    @Column(nullable = false)
    private String total_point;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
