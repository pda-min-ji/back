package king_min_ji_server.demo.converter;

import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.repository.UserRepository;
import king_min_ji_server.demo.web.dto.ProfileResponseDTO;
import king_min_ji_server.demo.web.dto.QuestionResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ProfileResponseDTO UserToProfileResponse(User user) {

        List<QuestionResponseDTO.QuestionDTO> solvedQ = user.getUserQuestionsList().stream()
                .map(userQuestion -> QuestionResponseDTO.QuestionDTO.builder()
                        .id(userQuestion.getQuestion().getId())
                        .title(userQuestion.getQuestion().getTitle())
                        .url(userQuestion.getQuestion().getUrl())
                        .level(userQuestion.getQuestion().getLevel()) // 선택 사항
                        .build())
                .collect(Collectors.toList());

        return ProfileResponseDTO.builder()
                .bojId(user.getBojId())      // User 객체에서 bojId를 가져와서 설정
                .name(user.getName())        // User 객체에서 name을 가져와서 설정
                .solvedQ(solvedQ)
                .build();
    }


}
