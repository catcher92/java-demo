package com.catcher92.demo.design.proxy.dynamic.cglib.test;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.dynamic.cglib.DBQueryCglibProxy;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class Test {

    public static void main(String[] args) {
        IDBQuery idbQuery = DBQueryCglibProxy.createProxy();
        System.out.println(idbQuery.request());
    }

}
