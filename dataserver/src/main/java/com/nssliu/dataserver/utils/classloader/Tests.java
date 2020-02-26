package com.nssliu.dataserver.utils.classloader;

import java.lang.reflect.Field;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/24 19:03
 * @describe:
 */
public class Tests {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
        Class c = classloader.loadClass("Smdtv_1");
        Field[] declaredFields = c.getDeclaredFields();
        System.out.println(declaredFields[0]);
    }
}
