package king_min_ji_server.demo.domain;

import jakarta.persistence.*;
import king_min_ji_server.demo.domain.common.BaseEntity;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Week_ranking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;
    // 순위를 나타내는 컬럼
    private Integer ranking;

    // 사용자 식별을 위한 ID
    private Long userId;

    // 유저의 점수
    private Integer point;

    @Column(nullable = false, length = 30)
    private String imgPath;

    @Column(nullable = false, length = 30)
    private String bojId;


    // 배치 작업에서 순위를 업데이트할 때 사용할 메서드
    public void updateRank(int newRank) {
        this.ranking = newRank;
    }

    public void updateScore(int newScore) {
        this.point = newScore;
    }
}