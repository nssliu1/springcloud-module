package com.nssliu.dataserver.utils.python;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 17:08
 * @describe:
 */
public class ExecPythonUtils {
    public static String execPython(String path,String filename){
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+path + filename);
            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
        } catch (IOException e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        return result;
    }
}
