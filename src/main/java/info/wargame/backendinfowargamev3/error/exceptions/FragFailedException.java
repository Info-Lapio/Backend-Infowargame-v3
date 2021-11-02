package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class FragFailedException extends InfoWarGameException {
    public FragFailedException() {
        super(ErrorCode.FRAG_FAILED);
    }
}
