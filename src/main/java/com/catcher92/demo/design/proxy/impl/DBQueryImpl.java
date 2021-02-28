package com.catcher92.demo.design.proxy.impl;

import com.catcher92.demo.design.proxy.IDBQuery;

import java.text.MessageFormat;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class DBQueryImpl implements IDBQuery{

    public DBQueryImpl() {
        System.out.println("构造被代理对象");
        // 线程休眠模拟构造对象耗时
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String request() {
        System.out.println("真实业务查询中...");
        return MessageFormat.format("请求结果:{0}", System.currentTimeMillis());
    }
}
