package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.dto.DeptLevelDto;
import io.github.futurewl.imooc.java.authority.management.param.DeptParam;
import io.github.futurewl.imooc.java.authority.management.service.SysDeptService;
import io.github.futurewl.imooc.java.authority.management.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-19:14:17
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysTreeService sysTreeService;

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/tree.json")
    public JsonData tree() {
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData update(DeptParam deptParam) {
        sysDeptService.update(deptParam);
        return JsonData.success();
    }

}
