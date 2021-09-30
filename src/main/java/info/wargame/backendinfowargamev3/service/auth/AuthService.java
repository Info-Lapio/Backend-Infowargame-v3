package info.wargame.backendinfowargamev3.service.auth;

import info.wargame.backendinfowargamev3.payload.request.SignInRequest;
import info.wargame.backendinfowargamev3.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse signInAdmin(SignInRequest signInRequest);
    TokenResponse signIn(SignInRequest signInRequest);
    TokenResponse refreshToken(String refreshToken, String accessToken);
}
