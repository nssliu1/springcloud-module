package com.nssliu.dataserver.test;

import com.nssliu.dataserver.utils.python.ExecPythonUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/22 9:28
 * @describe:
 */
public class TestPy {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("cc.py");
        System.out.println(resource);
    }
}
