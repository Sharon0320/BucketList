package sharon.bucketlist.global.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON_404",
            "잘못된 요청입니다."),
    VALID_FAIL(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS,"COMMON_400","유효성 검사 실패."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"COMMON_500","서버 오류입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
