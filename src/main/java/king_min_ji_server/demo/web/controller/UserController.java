package king_min_ji_server.demo.web.controller;

import king_min_ji_server.demo.config.JwtUtil;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.service.UserService;
import king_min_ji_server.demo.web.dto.SignUpRequestDto;
import king_min_ji_server.demo.web.dto.LoginRequestDto;
import king_min_ji_server.demo.web.dto.UserProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("register successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequest) {
        User user = userService.login(loginRequest.getName(), loginRequest.getPassword());

        if (user!=null) {
            String token = jwtUtil.generateToken(loginRequest.getName()); // JWT 토큰 생성
            UserProfileResponseDto response = new UserProfileResponseDto( user.getName(),"Bearer " + token,user.getBojId());
            return ResponseEntity.ok(response);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @GetMapping("/test")
    public ResponseEntity<String> getUserName(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userDetails.getUsername());
    }

}