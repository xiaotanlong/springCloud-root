package com.eureka.common.kafka;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.common.kafka
 * @Author tanjianglong
 * @CreatedTime 2017/10/25.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class KafkaConsumer {
    private final ConsumerConnector consumer;

    private KafkaConsumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", "10.60.96.142:2182");

        //group 代表一个消费组
        props.put("group.id", "jd-group");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        /*zk follower落后于zk leader的最长时间*/
        props.put("zookeeper.sync.time.ms", "200");
        /**	consumer向zookeeper提交offset的频率，单位是秒 */
        props.put("auto.commit.interval.ms", "1000");
        /** zookeeper中没有初始化的offset时，如果offset是以下值的回应： smallest：自动复位offset为smallest的offset
         largest：自动复位offset为largest的offset ;anything  else：向consumer抛出异常
         */
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(KafkaProducer.TOPIC).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext())
            System.out.println(it.next().message());
    }

    public static void main(String[] args) {
        new KafkaConsumer().consume();
    }
}
