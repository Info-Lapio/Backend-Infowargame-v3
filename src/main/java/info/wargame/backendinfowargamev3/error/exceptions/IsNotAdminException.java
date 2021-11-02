package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class IsNotAdminException extends InfoWarGameException {
    public IsNotAdminException() {
        super(ErrorCode.IS_NOT_ADMIN);
    }
}
