package com.nssliu.dataserver.utils.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/14 15:56
 * @describe:
 */
public class MyClassLoader1 extends ClassLoader {
    String path;
    String name;
    public MyClassLoader1(){

    }

    public MyClassLoader1(String path, String name) {
        this.path = path;
        this.name = name;
    }

    /*@Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }*/

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = new byte[1];
        try {
            b = loadClassByteArray(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name,b,0,b.length);
    }


    //将class文件字节流加载进来//可以执行网络流等class文件
    public byte[] loadClassByteArray(String name) throws IOException {
        name = path+name+".class";
        FileInputStream fileInputStream = new FileInputStream(new File(name));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while ((i = fileInputStream.read())!=-1){
            byteArrayOutputStream.write(i);
        }
        fileInputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();

    }
}
