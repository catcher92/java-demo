package com.catcher92.demo.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by caoxuedong on 2017/4/7.
 */
public class Producer {

    // 默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    private static final String BROKEURL = "tcp://192.168.56.10:61616";

    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS Provider 的连接
    private Connection connection;
    // Session： 一个发送或接收消息的线程
    private Session session;
    // Destination ：消息的目的地;消息发送给谁.
    private Destination destination;

    public void init() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("FirstQueue");
    }

    public void close() throws JMSException {
        connection.close();
    }

    public void testSend() throws JMSException, InterruptedException {
        // MessageProducer：消息发送者
        MessageProducer producer = session.createProducer(destination);
        TextMessage textMessage;
        for (int i = 0;i<100;i++) {
            textMessage = session.createTextMessage("Hello "+System.currentTimeMillis());
            producer.send(textMessage);
            session.commit();
            Thread.sleep(1000L);
        }
    }

    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        producer.init();
        System.out.println("init");
        producer.testSend();
        System.out.println("testSend");
        producer.close();
        System.out.println("close");
    }
}
