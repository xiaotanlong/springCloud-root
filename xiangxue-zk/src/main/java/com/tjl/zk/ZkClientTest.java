package com.tjl.zk;

import com.tjl.zk.lock.DistributeLock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 使用 org.apache.curator  操作 zookeeper
 * @date 2019/1/23 11:37
 */
public class ZkClientTest {
    public static Integer num = 0;
    public static Integer Thread_num = 200;
    private static CountDownLatch count = new CountDownLatch(Thread_num);

    public static void main(String[] args) throws Exception{

        for (int i = 0;i< Thread_num;i++){
            new Thread(()->{
                DistributeLock distributeLock = new DistributeLock("curator");
                try {
                    if(distributeLock.tryLock(50, TimeUnit.SECONDS)){
                        Random random = new Random();
                        Thread.sleep((long)random.nextInt(10));
                        ZkClientTest tets = new ZkClientTest();
                        tets.addNum();
                    }
                    count.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (Exception e2){
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
