package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class NoticeNotFoundException extends InfoWarGameException {
    public NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
