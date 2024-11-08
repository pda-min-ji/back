package king_min_ji_server.demo.service;

import jakarta.transaction.Transactional;
import king_min_ji_server.demo.domain.CustomUserDetails;
import king_min_ji_server.demo.converter.UserConverter;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.repository.UserRepository;
import king_min_ji_server.demo.web.dto.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import king_min_ji_server.demo.repository.UserRepository;
import king_min_ji_server.demo.web.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    // 생성자 주입
//    @Autowired
//    public UserService(UserRepository userRepository, UserConverter userConverter) {
//        this.userRepository = userRepository;
//        this.userConverter = userConverter;
//    }

    @Transactional
    public User signUp(SignUpRequestDto signUpRequestDto) {
        // boj_id 중복 확인
        if (userRepository.existsByBojId(signUpRequestDto.getBojId())) {
            throw new IllegalArgumentException("이미 존재하는 백준 ID입니다.");
        }
        String encryptedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        // User 엔티티 생성 및 저장
        User user = User.builder()
                .name(signUpRequestDto.getName())
                .bojId(signUpRequestDto.getBojId())
                .password(encryptedPassword)
                .build();

        return userRepository.save(user);
    }

    public User login(String name, String rawPassword) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return passwordEncoder.matches(rawPassword, user.getPassword()) ? user : null;
    }

    public ProfileResponse getUserProfile(String bojId) {
        User user = userRepository.findByBojId(bojId)
                .orElseThrow(() -> new RuntimeException("User not found"));  // 해당 bojId로 User를 찾지 못하면 예외 발생
        // UserConverter를 사용하여 User 객체를 ProfileResponse로 변환
        return userConverter.UserToProfileResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + name));
        return new CustomUserDetails(user); // User를 CustomUserDetails로 변환하여 반환
    }
}
