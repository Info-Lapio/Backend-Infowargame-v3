package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class ProblemFileNotFoundException extends InfoWarGameException {
    public ProblemFileNotFoundException() {
        super(ErrorCode.PROBLEM_FILE_NOT_FOUND);
    }
}
