package com.catcher92.demo.remote.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by caoxuedong on 2017/4/7.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 10000);
        // OrderService proxy = RPC.getProxy(OrderService.class, 1L, address, new Configuration());
        OrderService proxy = RPC.waitForProxy(OrderService.class, 1L, address, new Configuration());
        Writable allOrder = proxy.getAllOrder();
        System.out.println(allOrder);
        Object order = proxy.getOrderById(1);
        System.out.println(order);
        String str = proxy.sayHello();
        System.out.println(str);
    }

}
