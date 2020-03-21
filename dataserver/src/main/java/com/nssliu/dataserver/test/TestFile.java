package com.nssliu.dataserver.test;

import com.nssliu.dataserver.utils.PropertiesUtils.PropertiesUtil;

import java.io.File;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/21 21:40
 * @describe:
 */
public class TestFile {
    public static void main(String[] args) {
        String jarRoot = PropertiesUtil.getProperties_1("jarRoot");
        System.out.println(jarRoot);
        File file = new File(jarRoot);
        if(file.exists()){
            String[] list = file.list();
            for (String jarFile:list)
                System.out.println(file.getPath()+File.separator+jarFile);
        }
    }
}
