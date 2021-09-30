package info.wargame.backendinfowargamev3.service.auth;

import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.entity.user.enums.UserAuthority;
import info.wargame.backendinfowargamev3.payload.request.SignInRequest;
import info.wargame.backendinfowargamev3.payload.response.TokenResponse;
import info.wargame.backendinfowargamev3.security.JwtProvider;
import info.wargame.backendinfowargamev3.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationFacade authenticationFacade;

    @Value("${jwt.prefix}")
    private String prefix;

    @Override
    public TokenResponse signInAdmin(SignInRequest signInRequest) {
        return userRepository.findByEmail(signInRequest.getEmail())
                .filter(user -> passwordEncoder.matches(signInRequest.getPassword(), user.getPassword()) && user.getUserAuthority().equals(UserAuthority.ADMIN))
                .map(user -> {
                    String accessToken = jwtProvider.generateAccessToken(passwordEncoder.encode(user.getEmail()));
                    String refreshToken = jwtProvider.generateRefreshToken(passwordEncoder.encode(accessToken), user.getEmail());

                    return TokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .tokenType(prefix)
                            .build();
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {
        return userRepository.findByEmail(signInRequest.getEmail())
                .filter(user -> passwordEncoder.matches(signInRequest.getPassword(), user.getPassword()))
                .map(user -> {
                    String accessToken = jwtProvider.generateAccessToken(passwordEncoder.encode(user.getEmail()));
                    String refreshToken = jwtProvider.generateRefreshToken(passwordEncoder.encode(accessToken), user.getEmail());

                    return TokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .tokenType(prefix)
                            .build();
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken, String accessToken) {
        if(!jwtProvider.getEmail(refreshToken).equals(accessToken)) {
            throw new RuntimeException("Invalid Token");
        }
        if(!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid Token");
        }
        if(!jwtProvider.isRefreshToken(refreshToken)) {
            throw new RuntimeException("is not refresh token");
        }

        String email = jwtProvider.getEmailWithRefreshToken(refreshToken);
        String access = jwtProvider.generateAccessToken(email);

        return TokenResponse.builder()
                .accessToken(access)
                .refreshToken(jwtProvider.generateRefreshToken(passwordEncoder.encode(access), email))
                .tokenType(prefix)
                .build();
    }
}
