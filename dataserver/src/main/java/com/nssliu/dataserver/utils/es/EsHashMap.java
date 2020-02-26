package com.nssliu.dataserver.utils.es;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/26 18:01
 * @describe:
 */
public class EsHashMap<T,E> extends HashMap<String,String> {
    public static final Map<String,String> JAVA_TO_ES = new HashMap<String,String>();
    static {//key是java的类型， value是es类型
        JAVA_TO_ES.put("class java.lang.String","text");
        JAVA_TO_ES.put("class java.lang.Integer","integer");
        JAVA_TO_ES.put("class java.util.Date","date");
        JAVA_TO_ES.put("class java.lang.Boolean","boolean");
        JAVA_TO_ES.put("class java.lang.geojson","geo_point");
        JAVA_TO_ES.put("class java.lang.Double","double");
        JAVA_TO_ES.put("class java.lang.Long","long");
        /*JAVA_TO_ES.put("","");
        JAVA_TO_ES.put("","");*/
    }
    @Override
    public String put(String key, String value) {
        String s = JAVA_TO_ES.get(value);
        return super.put(key, s);
    }
}
