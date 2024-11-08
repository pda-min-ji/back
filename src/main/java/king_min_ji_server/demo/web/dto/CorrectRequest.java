package king_min_ji_server.demo.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CorrectRequest {
    private String bojId;
    private int questionNumber;
    // 기본 생성자 추가
    public CorrectRequest() {
    }
}
