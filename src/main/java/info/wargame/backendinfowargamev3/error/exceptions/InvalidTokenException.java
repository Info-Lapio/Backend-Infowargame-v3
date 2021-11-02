package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class InvalidTokenException extends InfoWarGameException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
