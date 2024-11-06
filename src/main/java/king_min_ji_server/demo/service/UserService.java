package king_min_ji_server.demo.service;

import jakarta.transaction.Transactional;
import king_min_ji_server.demo.domain.CustomUserDetails;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.repostiory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signUp(String name, String bojId, String password) {
        // boj_id 중복 확인
        if (userRepository.existsByBojId(bojId)) {
            throw new IllegalArgumentException("이미 존재하는 백준 ID입니다.");
        }
        String encryptedPassword = passwordEncoder.encode(password);
        // User 엔티티 생성 및 저장
        User user = User.builder()
                .name(name)
                .bojId(bojId)
                .password(encryptedPassword)
                .build();

        return userRepository.save(user);
    }
    public boolean login(String name, String rawPassword) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name: " + name);
        }
        return new CustomUserDetails(user); // User를 CustomUserDetails로 변환하여 반환
    }
}