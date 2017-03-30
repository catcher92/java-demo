package com.catcher92.demo.design.proxy.dynamic.javassist.test;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.dynamic.javassist.DBQueryJavassistProxy;

/**
 * Created by caoxuedong on 2017/3/30.
 */
public class Test {

    public static void main(String[] args) {
        IDBQuery idbQuery = DBQueryJavassistProxy.createJavassistProxy();
        System.out.println(idbQuery.request());
    }

}
