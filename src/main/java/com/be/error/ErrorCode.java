                package com.be.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 Bad Request 잘못된 요청
    INVALID_JWT_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "지원하지 않는 JWT 토큰입니다."),
    KAKAOPAY_CANCEL(HttpStatus.BAD_REQUEST, "카카오페이 결제가 취소되었습니다."),
    KAKAOPAY_FAIL(HttpStatus.BAD_REQUEST, "카카오페이 결제가 실패했습니다."),
    WRONG_AMOUNT(HttpStatus.BAD_REQUEST, "잘못된 금액입니다."),

    // 401 Unauthorized 권한 없음
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "권한이 없는 사용자입니다"),

    // 403 Forbidden 금지됨
    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "인증되지 않은 사용자입니다."),

    // 404 Not Found 찾을 수 없음
    USER_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    ROUTINE_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 루틴입니다."),
    MISSION_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 미션입니다."),
    WISHLIST_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 위시리스트입니다."),
    NOTIFICATION_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 알림입니다.");

    // 409 Conflict 충돌

    // 410 Gone 사라짐

    // 500 Internal Server Error 내부 서버 오류

    // 501 Not Implemented 구현되지 않음

    private final HttpStatus httpStatus;
    private final String message;
}
