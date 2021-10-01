package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.payload.request.SignInRequest;
import info.wargame.backendinfowargamev3.payload.response.TokenResponse;
import info.wargame.backendinfowargamev3.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/v1/admin")
    public TokenResponse signInAdmin(@RequestBody SignInRequest signInRequest) {
        return authService.signInAdmin(signInRequest);
    }

    @PutMapping("/v1")
    public TokenResponse refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken,
                                      @RequestHeader("Authorization") String accessToken) {
        return authService.refreshToken(refreshToken, accessToken);
    }
}
