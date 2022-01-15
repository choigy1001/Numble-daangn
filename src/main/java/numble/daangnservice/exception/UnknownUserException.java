package numble.daangnservice.exception;

public class UnknownUserException extends RuntimeException {

    private ErrorCode errorCode;

    public UnknownUserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
