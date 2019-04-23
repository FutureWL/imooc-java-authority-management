package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.param.AclModuleParam;
import io.github.futurewl.imooc.java.authority.management.service.SysAclModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:11:41
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/sys/aclModule")
public class SysAclModuleController {

    @Resource
    private SysAclModuleService sysAclModuleService;

    @RequestMapping("/acl.page")
    public ModelAndView acl() {
        return new ModelAndView("acl");
    }

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveAclModule(AclModuleParam param) {

        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData update(AclModuleParam param) {

        return JsonData.success();
    }

}
