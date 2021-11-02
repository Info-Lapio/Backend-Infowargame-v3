package info.wargame.backendinfowargamev3.service.user;

import info.wargame.backendinfowargamev3.payload.request.SignUpRequest;
import info.wargame.backendinfowargamev3.payload.request.UpdateUserInfoRequest;
import info.wargame.backendinfowargamev3.payload.response.UserResponse;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    UserResponse getMyInfo();
    UserResponse getUserInfo(String email);
    void updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest);
}
