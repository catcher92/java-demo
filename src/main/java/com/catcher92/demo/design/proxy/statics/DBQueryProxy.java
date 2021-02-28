package com.catcher92.demo.design.proxy.statics;

import com.catcher92.demo.design.proxy.IDBQuery;
import com.catcher92.demo.design.proxy.impl.DBQueryImpl;

/**
 * Created by caoxuedong on 2017/3/29.
 */
public class DBQueryProxy implements IDBQuery{

    private IDBQuery idbQuery;

    public DBQueryProxy() {
        System.out.println("构造代理对象");
    }

    @Override
    public String request() {
        System.out.println("调用真实业务之前");
        // 只有调用真实业务方法时候才初始化真实对象
        if (null == idbQuery) {
            idbQuery = new DBQueryImpl();
        }
        String result = idbQuery.request();
        System.out.println("调用真实业务之后");
        return result;
    }
}
