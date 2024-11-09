package king_min_ji_server.demo.web.dto;
import king_min_ji_server.demo.domain.Point;
import king_min_ji_server.demo.domain.Question;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProfileResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileDTO{
        private String name;
        private String bojId;
        private Point point;
        private List<QuestionResponseDTO.QuestionDTO> solvedQ;
    }
    private String name;
    private String bojId;
    private Point point;
    private List<QuestionResponseDTO.QuestionDTO> solvedQ;
}
