package com.nssliu.dataserver.utils.cmd;

import java.io.IOException;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/25 17:56
 * @describe:
 */
public class Cmd {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("javac D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\Smdtv_1.java");
    }
    public static void cmds(String cmds){
        try {
            Runtime.getRuntime().exec(cmds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
