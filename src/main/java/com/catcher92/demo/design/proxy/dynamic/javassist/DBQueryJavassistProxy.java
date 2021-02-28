package com.catcher92.demo.design.proxy.dynamic.javassist;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.impl.DBQueryImpl;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * Created by caoxuedong on 2017/3/30.
 */
public class DBQueryJavassistProxy implements MethodHandler{
    private IDBQuery idbQuery;

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
        if (null == idbQuery) idbQuery = new DBQueryImpl();
        System.out.println("Javassist动态代理-调用真实业务之前");
        String request = idbQuery.request();
        System.out.println("Javassist动态代理-调用真实业务之后");
        return request;
    }

    public static IDBQuery createJavassistProxy() {
        ProxyFactory factory = new ProxyFactory();
        factory.setInterfaces(new Class[]{IDBQuery.class});
        Class proxyClass = factory.createClass();
        IDBQuery idbQuery = null;
        try {
            idbQuery = (IDBQuery) proxyClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ((ProxyObject)idbQuery).setHandler(new DBQueryJavassistProxy());
        return idbQuery;
    }
}
