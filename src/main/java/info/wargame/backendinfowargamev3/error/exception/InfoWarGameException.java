package info.wargame.backendinfowargamev3.error.exception;

import lombok.Getter;

@Getter
public class InfoWarGameException extends RuntimeException {
    private final ErrorCode errorCode;

    public InfoWarGameException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public InfoWarGameException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
