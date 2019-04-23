package io.github.futurewl.imooc.java.authority.management.common;

import io.github.futurewl.imooc.java.authority.management.model.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述：ThreadLocal 并发处理
 *
 * @author weilai create by 2019-04-23:09:26
 * @version 1.0
 */
public class RequestHolder {

    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(SysUser user) {
        userHolder.set(user);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remote() {
        userHolder.remove();
        requestHolder.remove();
    }

}
