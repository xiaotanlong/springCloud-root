package com.example.client.spi;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class Log4j implements Log{
    public  void execute(String message){
        System.out.println("log4j ...:" + message);
    }
}
