package com.catcher92.demo.design.proxy.dynamic.jdk;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.impl.DBQueryImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class DBQueryJDKProxy implements InvocationHandler{

    private IDBQuery idbQuery;

    public static IDBQuery createProxy() {
        IDBQuery jdkProxy = (IDBQuery)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {IDBQuery.class}, new DBQueryJDKProxy());
        return jdkProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理-调用真实业务之前");
        // 只有调用真实业务方法时候才初始化真实对象
        if (null == idbQuery) idbQuery = new DBQueryImpl();
        String result = idbQuery.request();
        System.out.println("jdk动态代理-调用真实业务之后");
        return result;
    }
}
