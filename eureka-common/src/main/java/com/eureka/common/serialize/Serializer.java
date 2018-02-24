package com.eureka.common.serialize;

/**
 * @Author Hugo.Wwg
 * @Since 2017-07-28
 */
public interface Serializer {

    byte[] encode(Object msg);

    <T> T decode(byte[] buf, Class<T> type);
}
