package com.nssliu.dataserver.trueversion.threadobserver;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 13:02
 * @describe:
 */
public class Main {
    public static void main(String[] args) {
        TheObserver<Observer> theObserver = new TheObserverThread<>();//维护一个全局变量
        Observer observer1 = new ObserverThread();
        Observer observer2 = new ObserverThread();
        theObserver.regist(observer1);
        theObserver.regist(observer2);
        theObserver.addTask("空气质量", new Runnable() {
            @Override
            public void run() {
                System.out.println("空气质量执行");
            }
        });
    }
}
