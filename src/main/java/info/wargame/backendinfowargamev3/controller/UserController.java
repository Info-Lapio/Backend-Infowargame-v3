package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.payload.request.SignUpRequest;
import info.wargame.backendinfowargamev3.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/v1")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
    }
}
