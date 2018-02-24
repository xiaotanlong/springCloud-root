package com.eureka.common.rediscluster.serialize;

/**
 * @Author Hugo.Wwg
 * @Since 2017-07-28
 */
public class SerializerFactory {

    public static Serializer getSerializer() {
        return new KryoSerializer();
    }
}
