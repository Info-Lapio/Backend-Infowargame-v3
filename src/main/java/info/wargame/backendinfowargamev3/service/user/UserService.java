package info.wargame.backendinfowargamev3.service.user;

import info.wargame.backendinfowargamev3.payload.request.SignUpRequest;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
}
