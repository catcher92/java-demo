package com.catcher92.demo.design.singleton;

/**
 * 懒汉式单例模式
 * 调用时候初始化对象实现了延迟加载
 * 对多线程支持不太好
 * Created by caoxuedong on 2017/3/29.
 */
public class LazySingleton {

    private LazySingleton() {
        System.out.println("构造对象结束"+getClass().getSimpleName());
    }

    private static LazySingleton instance;

    // 加入synchronized关键字防止多线程生成多个实例
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void test() {
        System.out.println("调用非对象方法");
    }
}
