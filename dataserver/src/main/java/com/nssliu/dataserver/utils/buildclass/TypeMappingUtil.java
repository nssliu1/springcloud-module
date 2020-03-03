package com.nssliu.dataserver.utils.buildclass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 17:42
 * @describe:
 */
public class TypeMappingUtil {
    private final static Map<String,String> PROPERTY_TYPE_MAPPING_COLUMN;
    private final static Map<String,String> PROPERTY_TYPE_PACKAGE;
    static {
        PROPERTY_TYPE_MAPPING_COLUMN = new HashMap<>();
        PROPERTY_TYPE_MAPPING_COLUMN.put("varchar","String");
        PROPERTY_TYPE_MAPPING_COLUMN.put("int4","Integer");
        PROPERTY_TYPE_MAPPING_COLUMN.put("int8","Long");
       // PROPERTY_TYPE_MAPPING_COLUMN.put("float8","Float");
        PROPERTY_TYPE_MAPPING_COLUMN.put("float8","Double");
        PROPERTY_TYPE_MAPPING_COLUMN.put("serial","Integer");
        PROPERTY_TYPE_MAPPING_COLUMN.put("timestamptz","Date");

        PROPERTY_TYPE_PACKAGE = new HashMap<>();
        PROPERTY_TYPE_PACKAGE.put("Date","java.util.Date");
        PROPERTY_TYPE_PACKAGE.put("String","java.lang.String");
        PROPERTY_TYPE_PACKAGE.put("float8","Float");
        PROPERTY_TYPE_PACKAGE.put("timestamptz","java.util.Date");
    }
    public static String getTypeOfTableType(String columnType){
        return PROPERTY_TYPE_MAPPING_COLUMN.get(columnType);
    }
    public static String getPackageOfPropertyType(String PropertyType){
        return PROPERTY_TYPE_PACKAGE.get(PropertyType);
    }
}
