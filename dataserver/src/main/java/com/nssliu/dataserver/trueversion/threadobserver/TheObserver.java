package com.nssliu.dataserver.trueversion.threadobserver;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 12:44
 * @describe:
 */
public interface TheObserver<T> {
    public void regist(T t);
    public void unRegist(T t);
    public void updateAllObserver();
    public void addTask(String name, Runnable runnable);
    public void removeTask(String name);
}
