package king_min_ji_server.demo.web.dto;
import king_min_ji_server.demo.domain.Point;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProfileResponse {
    private String name;
    private String bojId;
    private Point point;
}
