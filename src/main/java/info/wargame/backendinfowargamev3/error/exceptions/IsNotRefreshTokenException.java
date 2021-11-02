package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class IsNotRefreshTokenException extends InfoWarGameException {
    public IsNotRefreshTokenException() {
        super(ErrorCode.IS_NOT_REFRESH_TOKEN);
    }
}
