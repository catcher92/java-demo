package com.catcher92.demo.remote.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public interface OrderService extends Remote{

    List<Order> getAllOrder() throws RemoteException;
    Order getOrderById(int id) throws RemoteException;

}
