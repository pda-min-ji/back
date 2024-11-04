package king_min_ji_server.demo.apiPayload.code.status;


import king_min_ji_server.demo.apiPayload.code.BaseErrorCode;
import king_min_ji_server.demo.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum
ErrorStatus implements BaseErrorCode {


    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON5000", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON4000", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON4001", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON4003", "금지된 요청입니다."),

    // 사용자 관련 응답
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "사용자를 찾을수 없습니다."),
    LANGUAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4002", "설정 가능한 언어가 없습니다."),
    COUNSELOR_NOT_FOUND(HttpStatus.NOT_FOUND, "COUNSELOR4001", "상담사를 찾을수 없습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "USER4003", "유효하지 않은 JWT입니다."),


    // 요약문 관련 응답
    SUMMARY_NOT_FOUND(HttpStatus.NOT_FOUND, "SUMMARY4001", "요약본을 찾을수 없습니다."),


    // 센터 관련 응답
    CENTER_NOT_FOUND(HttpStatus.NOT_FOUND,"CENTER4001", "센터를 찾을수 없습니다."),
    LANGUAGE_NOT_MATCHING(HttpStatus.NOT_FOUND, "CENTER4003", "해당 언어의 상담사가 없습니다.");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;


    }
}