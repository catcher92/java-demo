package com.catcher92.demo.design.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class DBQueryCglibProxy implements MethodInterceptor{

    public DBQueryCglibProxy() {
        System.out.println("构造代理对象");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理-调用真实业务之前");
        Object request = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib动态代理-调用真实业务之后");
        return request;
    }
}
