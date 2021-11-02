package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.payload.request.SignUpRequest;
import info.wargame.backendinfowargamev3.payload.request.UpdateUserInfoRequest;
import info.wargame.backendinfowargamev3.payload.response.UserResponse;
import info.wargame.backendinfowargamev3.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponse getMyInfo() {
        return userService.getMyInfo();
    }

    @PostMapping
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
    }

    @PutMapping
    public void updateInfo(@RequestBody UpdateUserInfoRequest updateUserInfoRequest) {
        userService.updateUserInfo(updateUserInfoRequest);
    }
}
