package info.wargame.backendinfowargamev3.error.exceptions;

import info.wargame.backendinfowargamev3.error.exception.ErrorCode;
import info.wargame.backendinfowargamev3.error.exception.InfoWarGameException;

public class ProblemNotFoundException extends InfoWarGameException {
    public ProblemNotFoundException() {
        super(ErrorCode.PROBLEM_NOT_FOUND);
    }
}
