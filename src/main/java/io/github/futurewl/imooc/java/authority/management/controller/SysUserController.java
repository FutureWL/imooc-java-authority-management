package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.param.UserParam;
import io.github.futurewl.imooc.java.authority.management.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-20:15:06
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    public SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveUser(UserParam param) {
        sysUserService.save(param);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData update(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }


}
