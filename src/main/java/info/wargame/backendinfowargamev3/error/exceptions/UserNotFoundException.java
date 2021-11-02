package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class UserNotFoundException extends InfoWarGameException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
