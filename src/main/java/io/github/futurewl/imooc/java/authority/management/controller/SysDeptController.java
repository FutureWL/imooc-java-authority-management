package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.param.DeptParam;
import io.github.futurewl.imooc.java.authority.management.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.success();
    }

}
