package com.nssliu.dataserver.trueversion.entity;

import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 14:16
 * @describe:
 */
public class CallBackEntity {
    private String indexName;
    private String type;
    private String uri;
    private Class clazz;
    private String classFullName;
    private List list;

    public CallBackEntity() {
    }

    public CallBackEntity(String indexName, String type, String uri, Class clazz, String classFullName, List list) {
        this.indexName = indexName;
        this.type = type;
        this.uri = uri;
        this.clazz = clazz;
        this.classFullName = classFullName;
        this.list = list;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
