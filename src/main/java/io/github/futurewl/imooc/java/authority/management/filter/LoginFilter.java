package io.github.futurewl.imooc.java.authority.management.filter;

import io.github.futurewl.imooc.java.authority.management.common.RequestHolder;
import io.github.futurewl.imooc.java.authority.management.model.SysUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:09:43
 * @version 1.0
 */
@Slf4j
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");

        if (sysUser == null) {
            String path = "/signin.jsp";
            response.sendRedirect(path);
            return;
        }
        RequestHolder.add(sysUser);
        RequestHolder.add(request);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
