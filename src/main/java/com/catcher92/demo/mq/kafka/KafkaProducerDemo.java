package com.catcher92.demo.mq.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by catcher92 on 2017/1/15.
 */
public class KafkaProducerDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.IPANDPORT);
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, Constants.TIMEOUT);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        final KafkaProducer producer = new KafkaProducer(properties);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++) {
                    try {
                        producer.send(new ProducerRecord(Constants.TOPIC, "At thread send "+ i), new DemoCallBack());
                        Thread.sleep(100);
                    } catch (Exception e){
                        System.err.println("run:"+e.getLocalizedMessage());
                    }
                }
            }
        }).start();
    }

    private static class DemoCallBack implements Callback{

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            if (exception==null) {
                System.out.println("onCompletion:success:"+metadata.topic()+" "+metadata.partition()+" "+metadata.offset());
            } else {
                System.err.println("onCompletion:"+exception.getLocalizedMessage());
            }
        }
    }
}
