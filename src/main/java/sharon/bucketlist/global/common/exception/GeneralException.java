package sharon.bucketlist.global.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sharon.bucketlist.global.common.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
}
