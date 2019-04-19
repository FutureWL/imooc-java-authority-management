package io.github.futurewl.imooc.java.authority.management.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 功能描述：应用上下文帮助类
 *
 * @author weilai create by 2019-04-18:21:49
 * @version 1.0
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T popBean(Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBean(name, clazz);
    }

}
