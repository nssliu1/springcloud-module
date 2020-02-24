package com.nssliu.dataserver.utils.buildclass;


import java.io.Serializable;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/12 16:12
 * @describe:
 */
public class OrmEntity implements Serializable {
    private String packageName;
    private String className;
    private String classType;
    private Map<String,String> propertyMap;
    {
        propertyMap = new ClassHashMap();
    }

    public OrmEntity() {
    }

    public OrmEntity(String packageName, String className, String classType, Map<String, String> propertyMap) {
        this.packageName = packageName;
        this.className = className;
        this.classType = classType;
        this.propertyMap = propertyMap;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = ClassUtil.classNameFormat(className);
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
