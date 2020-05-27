package com.champlink.common.util.context;

import org.springframework.context.ApplicationContext;

/**
 * spring上下文工具类
 * 
 */
public class ContextUtils {
    /** 上下文 */
    private static ApplicationContext applicationContext;

    /**
     * 初始化上下文
     * 
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取上下文
     * 
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据name获取bean
     * 
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据class获取bean
     * @param aClass    bean的类型
     * @param <T>       泛型类型
     * @return          返回IOC中的Bean
     */
    public static <T> T getBean(Class<T> aClass) {
        return getApplicationContext().getBean(aClass);
    }
}
