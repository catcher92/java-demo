package com.catcher92.demo.mq.kafka;


/**
 * Created by catcher92 on 2017/1/15.
 */
public class Constants {

    public static final String IPANDPORT = "127.0.0.1:9092";
    /*127.0.0.1:9093,127.0.0.1:9094,127.0.0.1:9095*/ /*192.168.56.101:9092*/
    public static final int TIMEOUT = 10000;
    public static final String TOPIC = "topic-high";
    public static final String GROUP = "high-group";

    public static void main(String[] args) {
        System.out.println(Math.abs(GROUP.hashCode())%50);
        System.out.println(Math.abs(GROUP.hashCode())%4);
    }
}
