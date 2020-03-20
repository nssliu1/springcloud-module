package com.nssliu.dataserver.trueversion.threadobserver;

import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 12:51
 * @describe:
 */
public interface Observer {


    void updateTask(Map<String, Thread> tasks);
}
