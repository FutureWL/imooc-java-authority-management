package io.github.futurewl.imooc.java.authority.management.controller;

import io.github.futurewl.imooc.java.authority.management.common.JsonData;
import io.github.futurewl.imooc.java.authority.management.exception.PermissionException;
import io.github.futurewl.imooc.java.authority.management.param.TestVo;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-18:14:22
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new RuntimeException("runtime exception");
//        throw new PermissionException("test exception");
//        return JsonData.success("hello,permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) {
        log.info("validate");
        try {
            Map<String, String> map = BeanValidator.validateObject(vo);
            if (map != null && map.entrySet().size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    log.info("{}-{}", entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {

        }
        return JsonData.success("test validate");
    }

}
