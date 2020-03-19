package com.nssliu.dataserver.utils.python;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 16:34
 * @describe:
 */
public class ExecPython {

    public static void main(String[] args) {
        String result = "";
        String filename = "nssliu";
        try {
            Process process = Runtime.getRuntime().exec("python E:\\supermap\\work\\2020\\长春\\空气质量\\cc.py " + filename);
//            process.waitFor();
            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
//            process.waitFor();
        } catch (IOException e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        //return result;
        System.out.println(result);
    }
}
