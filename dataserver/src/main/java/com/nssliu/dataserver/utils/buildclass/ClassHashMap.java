package com.nssliu.dataserver.utils.buildclass;

import java.util.HashMap;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/12 16:32
 * @describe:
 */
public class ClassHashMap extends HashMap<String,String> {
    @Override
    public String put(String columnName, String columnType) {
        columnName = columnName.toLowerCase();
        columnType = TypeMappingUtil.getTypeOfTableType(columnType);
        return super.put(columnName, columnType);
    }
}
