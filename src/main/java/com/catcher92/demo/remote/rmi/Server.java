package com.catcher92.demo.remote.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        // 指定server端的网卡
        // 否则linux下多网卡的机器会导致客户端连接时候获取的连接地址为127.0.0.1导致无法连接服务端
        System.setProperty("java.rmi.server.hostname","192.168.56.10");
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind("orderService", new OrderServiceImpl());
        System.out.println("rmi服务端启动");
    }

}
