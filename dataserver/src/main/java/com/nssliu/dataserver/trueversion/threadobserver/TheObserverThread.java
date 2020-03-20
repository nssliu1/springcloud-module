package com.nssliu.dataserver.trueversion.threadobserver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 12:48
 * @describe:
 */
public class TheObserverThread<T> implements TheObserver<T> {
    private List<T> allObserver = new ArrayList<>();
    private Map<String,Thread> tasks = new HashMap<>();
    @Override
    public void regist(T t) {
        allObserver.add(t);
    }

    @Override
    public void unRegist(T t) {
        int i = allObserver.indexOf(t);
        if(i >= 0){
            allObserver.remove(i);
        }
    }

    @Override
    public void updateAllObserver() {
        for (T obs : allObserver){
            Observer obss = (Observer) obs;
            obss.updateTask(this.tasks);
        }
    }

    @Override
    public void addTask(String name, Runnable runnable) {
        Thread thread = new Thread(runnable);
        this.tasks.put(name,thread);
        thread.start();
        updateAllObserver();
    }

    @Override
    public void removeTask(String name) {
        Thread removeTask = this.tasks.remove(name);
        removeTask.interrupt();

    }
}
