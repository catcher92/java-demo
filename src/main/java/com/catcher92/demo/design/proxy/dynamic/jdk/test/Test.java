package com.catcher92.demo.design.proxy.dynamic.jdk.test;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.dynamic.jdk.DBQueryJDKProxy;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class Test {

    public static void main(String[] args) {
        // 获取代理类
        IDBQuery proxy = DBQueryJDKProxy.createProxy();
        // 通过代理类调用真实类的方法
        System.out.println(proxy.request());
    }

}
