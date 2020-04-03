package com.example.rocketmq.rocketmq.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 0217319
 * @version V1.0
 * @Description:
 * @date 2019/3/14 14:33
 */
public class CountDownTest<T> implements Serializable{
    private static ConcurrentHashMap<String,CountDownTest> CountDownPoll = new ConcurrentHashMap<>();
    private T resurt;
    private int n;
    private CountDownLatch countDownLatch;
    private Date intDate;
    public CountDownTest(int n){
        this.n = n;
        countDownLatch = new CountDownLatch(n);
        this.intDate = new Date();
    }
    public void await() throws IllegalArgumentException, InterruptedException {
        this.countDownLatch.await();
    }
    public void await(long timeout, TimeUnit unit ) throws IllegalArgumentException, InterruptedException {
        this.countDownLatch.await(timeout,unit);
    }
    public void countDown() throws IllegalArgumentException, InterruptedException {
        this.countDownLatch.countDown();
    }

    public T getResurt() {
        return resurt;
    }

    public void setResurt(T resurt) {
        this.resurt = resurt;
    }

    public static void putObj(String key,CountDownTest countDownTest){
        CountDownPoll.put(key,countDownTest);
    }
    public static CountDownTest getObj(String key){
        return CountDownPoll.get(key);
    }

    public Date getIntDate() {
        return intDate;
    }
}
