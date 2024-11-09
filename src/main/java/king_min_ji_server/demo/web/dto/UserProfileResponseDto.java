package king_min_ji_server.demo.web.dto;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDto {
    private String name;
    private String token;
    private String bojId;
    private Long id;
}
