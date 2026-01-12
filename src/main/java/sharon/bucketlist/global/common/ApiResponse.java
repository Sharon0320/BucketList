package sharon.bucketlist.global.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sharon.bucketlist.global.common.code.BaseErrorCode;
import sharon.bucketlist.global.common.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "data"})
public class ApiResponse<T> {

    @JsonProperty("success")
    private final Boolean success;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private T data;


    // 성공한 경우 (data 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T data) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), data);
    }

    // 실패한 경우 (data 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T data) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), data);
    }
}
