package com.catcher92.demo.design.proxy.statics.test;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.statics.DBQueryProxy;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class Test {

    public static void main(String[] args) {
        // 代理类初始化很快
        IDBQuery idbQuery = new DBQueryProxy();
        // 通过代理类调用业务方法
        System.out.println(idbQuery.request());
    }

}
