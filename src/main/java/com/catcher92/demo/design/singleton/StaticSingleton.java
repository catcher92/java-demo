package com.catcher92.demo.design.singleton;

/**
 * 饿汉式单例模式
 * 实现了延迟加载，又不用担心多线程问题
 * Created by caoxuedong on 2017/3/29.
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("构造对象结束"+getClass().getSimpleName());
    }

    public static StaticSingleton getInstance() {
        return StaticSingletonHolder.instance;
    }

    public static void test() {
        System.out.println("调用非对象方法");
    }

    private static class StaticSingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }
}
