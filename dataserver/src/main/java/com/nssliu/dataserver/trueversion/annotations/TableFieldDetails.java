package com.nssliu.dataserver.trueversion.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/16 13:22
 * @describe:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableFieldDetails {
    String esType();//存入es的类型
    //boolean isSave() default true;
    String esName() default "";//es的类型名
    String param() default "";//附加属性名
    String paramDetail() default "";//附加属性描述

}
