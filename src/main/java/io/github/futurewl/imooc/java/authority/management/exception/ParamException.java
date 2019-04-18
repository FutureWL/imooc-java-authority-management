package io.github.futurewl.imooc.java.authority.management.exception;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-18:16:45
 * @version 1.0
 */
public class ParamException extends RuntimeException {

    public ParamException() {
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
