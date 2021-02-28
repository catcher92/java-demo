package com.catcher92.demo.remote.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String url = "rmi://192.168.56.10";
        OrderService orderService = (OrderService)Naming.lookup(url+":"+ Registry.REGISTRY_PORT+"/orderService");
        List<Order> allOrder = orderService.getAllOrder();
        System.out.println(allOrder);
        Order order = orderService.getOrderById(1);
        System.out.println(order);
    }

}
