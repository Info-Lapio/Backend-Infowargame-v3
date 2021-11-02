package info.wargame.backendinfowargamev3.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_TOKEN(403, "Invalid Token"),
    LOGIN_FAILED(403, "Login Failed"),
    IS_NOT_REFRESH_TOKEN(403, "Is Not Refresh Token"),
    IS_NOT_ADMIN(403, "Is Not Admin"),
    PROBLEM_FILE_NOT_FOUND(404, "Problem File Not Found"),
    FRAG_FAILED(403, "Frag Failed"),
    EVENT_NOT_FOUND(404, "Event Not Found"),
    EVENT_IMAGE_NOT_FOUND(404, "Event Image Not Found"),
    NOTICE_NOT_FOUND(404, "Notice Not Found"),
    PROBLEM_NOT_FOUND(404, "Problem Not Found"),
    USER_NOT_FOUND(404, "User Not Found");

    private int status;
    private String message;
}
