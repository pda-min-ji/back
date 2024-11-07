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
public class Ranking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 순위를 나타내는 컬럼
    @Column(name = "`rank`")
    private Integer rank;

    // 사용자 식별을 위한 ID
    private Long userId;

    // 유저의 점수
    private Integer score;

    // 배치 작업에서 순위를 업데이트할 때 사용할 메서드
    public void updateRank(int newRank) {
        this.rank = newRank;
    }

    public void updateScore(int newScore) {
        this.score = newScore;
    }
}