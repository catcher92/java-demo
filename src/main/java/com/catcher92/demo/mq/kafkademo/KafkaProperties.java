package com.catcher92.demo.mq.kafkademo;

/**
 * Created by catcher92 on 2017/1/14.
 */
public class KafkaProperties {
    public static final String TOPIC = "test";
    public static final String KAFKA_SERVER_URL = "192.168.56.101";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String TOPIC2 = "topic2";
    public static final String TOPIC3 = "topic3";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    private KafkaProperties() {}
}
