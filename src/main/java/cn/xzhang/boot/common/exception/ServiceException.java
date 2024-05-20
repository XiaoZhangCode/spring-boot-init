package cn.xzhang.boot.common.exception;

import cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑异常 Exception
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public final class ServiceException extends RuntimeException {
    /**
     * 业务错误码
     *
     * @see GlobalErrorCodeConstants
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
