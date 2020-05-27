package com.champlink.common.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验规则
 * 
 * @author natyu
 * @date 2016年3月29日 上午11:12:42
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {
    /**
     * 校验规则
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:12:49
     * @return
     */
    String rule() default "";

    /**
     * 提示语
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:12:54
     * @return
     */
    String msg() default "";
}
