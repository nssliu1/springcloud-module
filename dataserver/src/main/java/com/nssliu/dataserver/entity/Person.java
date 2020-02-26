package com.nssliu.dataserver.entity;

import java.io.Serializable;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/10/31 18:17
 * @describe:
 */
public class Person implements Serializable {
    private String id;
    private String name;


    public Person() {
    }

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
