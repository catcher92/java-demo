package com.catcher92.demo.jvm.classloader;

public class Hello {

    private static final String a = "hello-a";
    public static final String b = "hello-b";
    private static String c = "hello-c";
    public static String d = "hello-d";

    static {
        System.out.println("I am hello.");
    }

}
