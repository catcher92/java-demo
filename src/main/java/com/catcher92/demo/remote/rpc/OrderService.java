package com.catcher92.demo.remote.rpc;

import org.apache.hadoop.io.ArrayWritable;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public interface OrderService {

    public static final long versionID = 1L;

    ArrayWritable getAllOrder();
    Order getOrderById(int id);
    String sayHello();

}
