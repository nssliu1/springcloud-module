package com.nssliu.dataserver.utils.buildclass;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 16:48
 * @describe:
 */
public class ClassUtil {
    public static String classNameFormat(String oldName){
        return oldName.substring(0, 1).toUpperCase() + oldName.substring(1);
    }
    public static String packageName(String filePath){
        String[] split = filePath.split("\\\\");
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<split.length;i++){
            sb.append(split[i]+".");
        }
        return sb.toString();
    }
}
