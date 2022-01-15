package numble.daangnservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    DUPLICATED_EMAIL("중복된 이메일 입니다.", 409),
    NOT_REGISTERED_USER("등록되지 않은 유저입니다.", 400);


    private String message;
    private Integer statusCode;

    ErrorCode(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
