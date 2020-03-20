package com.nssliu.dataserver.trueversion.factory;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 13:13
 * @describe:
 */
public interface LoopTask<T> {
    T getTask(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
