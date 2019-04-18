package io.github.futurewl.imooc.java.authority.management.exception;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-18:15:44
 * @version 1.0
 */
public class PermissionException extends RuntimeException  {

    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    protected PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
