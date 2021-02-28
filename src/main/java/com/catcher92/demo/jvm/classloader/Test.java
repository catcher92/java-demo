package com.catcher92.demo.jvm.classloader;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws Exception {
        Class<?> hello = getHelloFromClassloader();
        System.out.println("加载class了");
        Field a = hello.getDeclaredField("a");
        a.setAccessible(true);
        System.out.println("a=" + a.get(hello));
        System.out.println("b=" + hello.getField("b").get(hello));
        Field c = hello.getDeclaredField("c");
        c.setAccessible(true);
        System.out.println("c=" + c.get(hello));
        System.out.println("d=" + hello.getField("d").get(hello));
    }

    private static Class<?> getHelloFromForName() throws ClassNotFoundException {
        // 会执行静态代码块
        return Class.forName("com.catcher92.demo.jvm.classloader.Hello");
    }

    private static Class<?> getHelloFromClassloader() throws ClassNotFoundException {
        // 不会执行静态代码块
        ClassLoader loader = Hello.class.getClassLoader();
        return loader.loadClass("com.catcher92.demo.jvm.classloader.Hello");
    }
}
