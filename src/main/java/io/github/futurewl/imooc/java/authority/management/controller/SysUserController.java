package io.github.futurewl.imooc.java.authority.management.controller;

import com.google.common.collect.Maps;
import io.github.futurewl.imooc.java.authority.management.beans.PageQuery;
import io.github.futurewl.imooc.java.authority.management.beans.PageResult;
import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.model.SysUser;
import io.github.futurewl.imooc.java.authority.management.param.UserParam;
import io.github.futurewl.imooc.java.authority.management.service.SysRoleService;
import io.github.futurewl.imooc.java.authority.management.service.SysTreeService;
import io.github.futurewl.imooc.java.authority.management.service.SysUserService;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

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

    @Resource
    public SysTreeService sysTreeService;

    @Resource
    public SysRoleService sysRoleService;

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

    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }

    @RequestMapping("/acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("userId") int userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("acls", sysTreeService.userAclTree(userId));
        map.put("roles", sysRoleService.getRoleListByUserId(userId));
        return JsonData.success(map);
    }

}
