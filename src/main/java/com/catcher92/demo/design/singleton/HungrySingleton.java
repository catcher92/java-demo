package com.catcher92.demo.design.singleton;

/**
 * 饿汉式单例模式
 * 类加载时候初始化对象无法延迟加载
 * Created by caoxuedong on 2017/3/29.
 */
public class HungrySingleton {

    private HungrySingleton() {
        System.out.println("构造对象结束"+getClass().getSimpleName());
    }

    // 方式一
    private static HungrySingleton instance = new HungrySingleton();

    // 方式二
    // private static HungrySingleton instance;
    //
    // static {
    //     instance = new HungrySingleton();
    // }

    public static HungrySingleton getInstance() {
        return instance;
    }

    public static void test() {
        System.out.println("调用非对象方法");
    }
}
