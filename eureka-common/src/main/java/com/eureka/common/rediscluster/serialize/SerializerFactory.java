package com.eureka.common.rediscluster.serialize;

/**
 */
public class SerializerFactory {

    public static Serializer getSerializer() {
        return new KryoSerializer();
    }
}
