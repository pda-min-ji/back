package king_min_ji_server.demo.converter;

import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.repository.UserRepository;
import king_min_ji_server.demo.web.dto.ProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ProfileResponse UserToProfileResponse(User user) {
        return ProfileResponse.builder()
                .bojId(user.getBojId())      // User 객체에서 bojId를 가져와서 설정
                .name(user.getName())        // User 객체에서 name을 가져와서 설정
                .build();
    }


}
