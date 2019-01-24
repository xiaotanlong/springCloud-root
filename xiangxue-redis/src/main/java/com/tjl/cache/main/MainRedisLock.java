package com.tjl.cache.main;

import com.tjl.cache.lock.RedisLock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: redis 分布式锁测试
 * @date 2019/1/24 14:48
 */
public class MainRedisLock {
    public static Integer num = 0;
    public static Integer Thread_num = 1;
    private static CountDownLatch count = new CountDownLatch(Thread_num);

    public static void main(String[] args) throws Exception{

        for (int i = 0;i< Thread_num;i++){
            new Thread(()->{
                RedisLock distributeLock = new RedisLock();
                try {
                    distributeLock.lock();

                        Random random = new Random();
                        //Thread.sleep((long)random.nextInt(1));
                        MainRedisLock tets = new MainRedisLock();
                        tets.addNum();

                    count.countDown();
                }/*catch (InterruptedException e){
                    e.printStackTrace();
                }*/catch (Exception e2){
                    e2.printStackTrace();
                }finally {
                    distributeLock.unlock();
                }
            }).start();
        }

        count.await();

        System.out.println("---------------"+num);
    }

    public void addNum(){
        num ++;
    }
}
