package king_min_ji_server.demo.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {
    private String name;
    private String token;
    private String bojId;


}
