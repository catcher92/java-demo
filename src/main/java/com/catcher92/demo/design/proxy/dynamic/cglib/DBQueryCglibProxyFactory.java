package com.catcher92.demo.design.proxy.dynamic.cglib;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.impl.DBQueryImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by caoxuedong on 2017/3/30.
 */
public class DBQueryCglibProxyFactory {

    public static final IDBQuery getInstance(DBQueryCglibProxy proxy) {
        Enhancer enhancer = new Enhancer();
        // 指定切入器
        enhancer.setCallback(proxy);
        // 指定实现的父类
        enhancer.setSuperclass(DBQueryImpl.class);
        // 生成代理类的实例
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        return cglibProxy;
    }

}
