package king_min_ji_server.demo.web.controller;

import king_min_ji_server.demo.config.JwtUtil;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.apiPayload.ApiResponse;
import king_min_ji_server.demo.service.UserService;
import king_min_ji_server.demo.web.dto.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//        this.jwtUtil = new JwtUtil();
//    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("register successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequest) {
        User user = userService.login(loginRequest.getName(), loginRequest.getPassword());

        if (user != null) {
            String token = jwtUtil.generateToken(loginRequest.getName());
            UserProfileResponseDto response = new UserProfileResponseDto(user.getName(), "Bearer " + token, user.getBojId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<ProfileResponse>> getUserProfile(@RequestParam(name = "bojId") String bojId) {
        ProfileResponse response = userService.getUserProfile(bojId);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }
}




