package com.eureka.common.kafka;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.common.kafka
 * @Author tanjianglong
 * @CreatedTime 2017/10/25.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class KafkaProducer {
    private final Producer<String, String> producer;
    public final static String TOPIC = "topic2";

    private KafkaProducer(){
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "10.60.96.142:9092");

        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        //request.required.acks
        /**
         * 此配置是表明当一次produce请求被认为完成时的确认值。特别是，多少个其他brokers必须已经提交了数据到他们的log并且向他们的leader确认了这些信息。典型的值包括：
         0： 表示producer从来不等待来自broker的确认信息（和0.7一样的行为）。这个选择提供了最小的时延但同时风险最大（因为当server宕机时，数据将会丢失）。
         1：表示获得leader replica已经接收了数据的确认信息。这个选择时延较小同时确保了server确认接收成功。
         -1：producer会获得所有同步replicas都收到数据的确认。同时时延最大，然而，这种方式并没有完全消除丢失消息的风险，因为同步replicas的数量可能是1.如果你想确保某些replicas接收到数据，那么你应该在topic-level设置中选项
         */
        props.put("request.required.acks","-1");

        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    void produce() {
        int messageNo = 10;
        final int COUNT = 100;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new KeyedMessage<String, String>(TOPIC, key ,data));
            System.out.println(data);
            messageNo ++;
        }
    }

    public static void main( String[] args )
    {
        new KafkaProducer().produce();
    }
}
