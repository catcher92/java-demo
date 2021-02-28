package com.catcher92.demo.remote.rpc;

import org.apache.hadoop.io.ArrayWritable;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public ArrayWritable getAllOrder() {
        ArrayWritable arrayWritable = new OrderArrayWritable();
        Order order = new Order();
        order.setId(1);
        order.setUsername("张三");
        order.setGoodname("商品1");

        Order order2 = new Order();
        order2.setId(2);
        order2.setUsername("李四");
        order2.setGoodname("商品2");

        arrayWritable.set(new Order[]{order, order2});
        return arrayWritable;
    }

    @Override
    public Order getOrderById(int id) {
        Order order = new Order();
        order.setId(id);
        switch (id) {
            case 1:
                order.setUsername("张三");
                order.setGoodname("商品1");
                break;
            case 2:
                order.setUsername("李四");
                order.setGoodname("商品2");
                break;
        }
        return order;
    }

    @Override
    public String sayHello() {
        return "Hello !";
    }
}
