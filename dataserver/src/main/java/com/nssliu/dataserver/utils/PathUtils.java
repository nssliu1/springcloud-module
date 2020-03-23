package com.nssliu.dataserver.utils;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/22 10:11
 * @describe:
 */
public class PathUtils {
    public static String getPath()
    {
        String path = PathUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if(System.getProperty("os.name").contains("dows"))
        {
            path = path.substring(1,path.length());
        }
        if(path.contains("jar"))
        {
            path = path.substring(0,path.lastIndexOf("."));
            return path.substring(0,path.lastIndexOf("/"));
        }
        return path.replace("target/classes/", "");
    }

    public static void main(String[] args) {
        System.out.println(PathUtils.getPath());
    }

}
