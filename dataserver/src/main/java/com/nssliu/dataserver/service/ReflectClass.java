package com.nssliu.dataserver.service;


import java.lang.reflect.Field;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 14:39
 * @describe:
 */

public class ReflectClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Class<?> clazz = Class.forName("com.nssliu.dataserver.service.Student");
        System.out.println(clazz);
        //获取属性
        Field[] fields = clazz.getDeclaredFields();
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        Object o = clazz.newInstance();
        name.set(o,"liuzhniss");
        Object o1 = name.get(o);
        System.out.println(o1);
        /*Field sno = clazz.getDeclaredField("sno");
        sno.setAccessible(true);
        sno.set(null,"nssliuzh");
        System.out.println(Student.sno);*/

        for (Field field:
             fields) {
            System.out.println(field.getName());
        }
    }
}
class Student{
    public static String sno;
    private String name;
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }
}
