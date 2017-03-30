package com.catcher92.demo.design.proxy.dynamic.cglib;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.impl.DBQueryImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class DBQueryCglibProxy implements MethodInterceptor{
    private IDBQuery idbQuery;

    public static IDBQuery createProxy() {
        Enhancer enhancer = new Enhancer();
        // 指定切入器
        enhancer.setCallback(new DBQueryCglibProxy());
        // 指定实现的接口
        enhancer.setInterfaces(new Class[]{IDBQuery.class});
        // 生成代理类的实例
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        return cglibProxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理-调用真实业务之前");
        if (null == idbQuery) idbQuery = new DBQueryImpl();
        String request = idbQuery.request();
        System.out.println("cglib动态代理-调用真实业务之后");
        return request;
    }
}
