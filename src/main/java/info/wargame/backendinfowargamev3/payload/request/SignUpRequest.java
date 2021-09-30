package info.wargame.backendinfowargamev3.payload.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String email;
    private String password;
    private String nickName;
}
