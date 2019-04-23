package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.model.SysUser;
import io.github.futurewl.imooc.java.authority.management.service.SysUserService;
import io.github.futurewl.imooc.java.authority.management.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:00:33
 * @version 1.0
 */
@Controller
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        String path = "signin.jsp";
        response.sendRedirect(path);
    }

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");
        if (StringUtils.isBlank(username)) {
            errorMsg = "用户名不能为空！";
        } else if (StringUtils.isBlank(password)) {
            errorMsg = "密码不能为空！";
        } else if (sysUser == null) {
            errorMsg = "查询不到指定用户！";
        } else if (!sysUser.getPassword().equals(MD5Util.encrypt(password))) {
            errorMsg = "用户名或密码错误！";
        } else if (sysUser.getStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员！";
        } else {
            // login success
            request.getSession().setAttribute("user", sysUser);
            if (StringUtils.isNotBlank(ret)) {
                response.sendRedirect(ret);
            } else {
                response.sendRedirect("/admin/index.page"); // todo
            }
            return;
        }
        request.setAttribute("error", errorMsg);
        request.setAttribute("username", username);
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        String path = "signin.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

}
