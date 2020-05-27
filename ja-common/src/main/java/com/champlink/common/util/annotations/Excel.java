package com.champlink.common.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel
 * 
 * @author natyu
 * @date 2016年3月29日 上午11:12:20
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    /**
     * 标题
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:12:27
     * @return
     */
    String title() default "";

    /**
     * 可否为空 true:可以为空 ,false:不可以为空
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:12:35
     * @return
     */
    boolean isNull() default true;
}
