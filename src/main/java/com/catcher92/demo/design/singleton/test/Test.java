package com.catcher92.demo.design.singleton.test;


import com.catcher92.demo.design.singleton.HungrySingleton;
import com.catcher92.demo.design.singleton.LazySingleton;
import com.catcher92.demo.design.singleton.StaticSingleton;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class Test {

    public static void main(String[] args) {
        HungrySingleton.test();
        LazySingleton.test();
        StaticSingleton.test();

        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();
        System.out.println(hungrySingleton1 == hungrySingleton2);

        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println(lazySingleton1 == lazySingleton2);

        StaticSingleton staticSingleton1 = StaticSingleton.getInstance();
        StaticSingleton staticSingleton2 = StaticSingleton.getInstance();
        System.out.println(staticSingleton1 == staticSingleton2);
    }

}
