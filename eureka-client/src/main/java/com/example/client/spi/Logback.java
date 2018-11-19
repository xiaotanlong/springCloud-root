package com.example.client.spi;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class Logback implements Log {
    @Override
    public void execute(String message) {
        System.out.println("Logback ...:" + message);
    }
}
