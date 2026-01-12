package sharon.bucketlist.global.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON_400",
            "잘못된 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
