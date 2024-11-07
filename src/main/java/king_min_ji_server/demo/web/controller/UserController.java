package king_min_ji_server.demo.web.controller;

import king_min_ji_server.demo.config.JwtUtil;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody User user) {
        userService.signUp(user.getName(), user.getBojId(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("register successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User loginRequest) {
        boolean isAuthenticated = userService.login(loginRequest.getName(), loginRequest.getPassword());

        if (isAuthenticated) {
            String token = jwtUtil.generateToken(loginRequest.getName()); // JWT 토큰 생성
            return ResponseEntity.ok().body("Bearer " + token);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}