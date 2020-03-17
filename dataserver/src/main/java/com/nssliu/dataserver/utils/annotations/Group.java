package com.nssliu.dataserver.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/16 13:37
 * @describe:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Group {
    String groupName();
    String groupType();
}
