package com.nssliu.dataserver.trueversion.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 13:13
 * @describe:
 */
public class JavaEsType {
    Map<String,List<Field>> groupMap;
    List<Field> fieldsList;

    public JavaEsType() {
    }

    public JavaEsType(Map<String, List<Field>> groupMap, List<Field> fieldsList) {
        this.groupMap = groupMap;
        this.fieldsList = fieldsList;
    }

    public Map<String, List<Field>> getGroupMap() {
        return groupMap;
    }

    public void setGroupMap(Map<String, List<Field>> groupMap) {
        this.groupMap = groupMap;
    }

    public List<Field> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<Field> fieldsList) {
        this.fieldsList = fieldsList;
    }
}
