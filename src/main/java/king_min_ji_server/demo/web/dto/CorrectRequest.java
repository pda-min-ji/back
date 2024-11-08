package king_min_ji_server.demo.web.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CorrectRequest {
    private String bojId;
    private int questionNumber;
}
