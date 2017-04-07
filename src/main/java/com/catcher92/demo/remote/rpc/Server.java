package com.catcher92.demo.remote.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by caoxuedong on 2017/4/7.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        RPC.Server server = new RPC.Builder(conf).setBindAddress("127.0.0.1").setProtocol(OrderService.class).setPort(10000).setInstance(new OrderServiceImpl()).build();
        server.start();
        System.out.println("server启动");
    }

}
