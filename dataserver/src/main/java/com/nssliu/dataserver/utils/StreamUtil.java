package com.nssliu.dataserver.utils;

import java.io.*;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 17:27
 * @describe:
 */
public class StreamUtil {
    public static OutputStreamWriter getWrite(String filePath1){
        OutputStreamWriter osw=null;
        try{
            String filePath = filePath1;
            File file = new File(filePath); //地址
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            FileOutputStream fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            return osw;
        }catch (Exception e){
            System.out.println(e);
        }
        return osw;
    }
    public static void flushWriter(OutputStreamWriter osw) throws IOException {
        osw.flush();
    }
    public static void closeWriter(OutputStreamWriter osw) throws IOException {
        osw.flush();
        osw.close();
    }
}
