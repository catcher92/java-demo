package com.catcher92.demo.remote.rpc;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public interface OrderService {

    public static final long versionID = 1L;

    List<Order> getAllOrder();
    Order getOrderById(int id);
    String sayHello();

}
