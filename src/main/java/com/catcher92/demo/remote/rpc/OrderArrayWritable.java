package com.catcher92.demo.remote.rpc;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;

/**
 * Created by caoxuedong on 2017/4/10.
 */
public class OrderArrayWritable extends ArrayWritable{

    public OrderArrayWritable() {
        super(Order.class);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Writable[] writables = get();
        for (int i = 0; i < writables.length; i++) {
            builder.append(writables[i]);
            if (i != writables.length-1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
