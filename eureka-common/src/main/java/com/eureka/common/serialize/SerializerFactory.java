package com.eureka.common.serialize;

/**
 */
public class SerializerFactory {

    public static Serializer getSerializer() {
        return new KryoSerializer();
    }
}
