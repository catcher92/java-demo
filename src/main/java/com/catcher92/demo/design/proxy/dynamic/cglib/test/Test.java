package com.catcher92.demo.design.proxy.dynamic.cglib.test;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.dynamic.cglib.DBQueryCglibProxy;
import com.catcher92.demo.design.proxy.dynamic.cglib.DBQueryCglibProxyFactory;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class Test {

    public static void main(String[] args) {
        DBQueryCglibProxy proxy = new DBQueryCglibProxy();
        IDBQuery idbQuery = DBQueryCglibProxyFactory.getInstance(proxy);
        System.out.println(idbQuery.request());
    }

}
