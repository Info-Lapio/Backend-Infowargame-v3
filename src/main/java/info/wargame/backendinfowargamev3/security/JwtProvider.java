package info.wargame.backendinfowargamev3.security;

import info.wargame.backendinfowargamev3.security.auth.AuthDetail;
import info.wargame.backendinfowargamev3.security.auth.AuthDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.access}")
    private long accessToken;

    @Value("${jwt.refresh}")
    private long refreshToken;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    private final AuthDetailService auth;

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessToken * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .claim("type", "access_token")
                .setSubject(email)
                .compact();
    }

    public String generateRefreshToken(String accessToken, String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshToken * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .claim("type", "refresh_token")
                .claim("email", email)
                .setSubject(accessToken)
                .compact();
    }

    public String resolvedToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(header);
        if(bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getSubject();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean isRefreshToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("type").equals("refresh_token");
    }

    public String getEmailWithRefreshToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("email").toString();
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        AuthDetail authDetail = auth.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(authDetail, "", authDetail.getAuthorities());
    }
}
