package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class EventImageNotFoundException extends InfoWarGameException {
    public EventImageNotFoundException() {
        super(ErrorCode.EVENT_IMAGE_NOT_FOUND);
    }
}
