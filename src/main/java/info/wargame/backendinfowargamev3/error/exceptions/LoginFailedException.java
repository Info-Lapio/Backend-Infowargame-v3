package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class LoginFailedException extends InfoWarGameException {
    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED);
    }
}
