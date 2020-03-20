package com.nssliu.dataserver.trueversion.threadobserver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 12:53
 * @describe:
 */
public class ObserverThread implements Observer{
    Map<String,Thread> tasks = new HashMap<>();
    @Override
    public void updateTask(Map<String, Thread> tasks) {
        this.tasks = tasks;
    }
}
