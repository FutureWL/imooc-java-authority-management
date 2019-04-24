package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.dto.AclModuleLevelDto;
import io.github.futurewl.imooc.java.authority.management.dto.DeptLevelDto;
import io.github.futurewl.imooc.java.authority.management.param.AclModuleParam;
import io.github.futurewl.imooc.java.authority.management.service.SysAclModuleService;
import io.github.futurewl.imooc.java.authority.management.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SysTreeService sysTreeService;


    @RequestMapping("/acl.page")
    public ModelAndView acl() {
        return new ModelAndView("acl");
    }

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveAclModule(AclModuleParam param) {
        sysAclModuleService.save(param);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/tree.json")
    public JsonData tree() {
        return JsonData.success(sysTreeService.aclModuleTree());
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData update(AclModuleParam param) {
        sysAclModuleService.update(param);
        return JsonData.success();
    }


    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonData delete(@RequestParam("id") int id) {
        sysAclModuleService.delete(id);
        return JsonData.success();
    }

}
