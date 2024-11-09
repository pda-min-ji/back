package king_min_ji_server.demo.web.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class QuestionResponseDTO {

    private Long id;
    private String url;
    private int number;
    private String title;
    private int level;
    private List<String> tags;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionDTO {
        private Long id;
        private String title;
        private String url;
        private int level;
    }
}
