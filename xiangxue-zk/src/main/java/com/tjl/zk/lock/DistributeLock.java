package com.tjl.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 使用 org.apache.curator  操作 zookeeper 实现分布式锁
 * @date 2019/1/23 13:51
 */
public class DistributeLock implements Lock ,Serializable {
    private static final long serialVersionUID = -849788834855571605L;
    private final static String zk_address = "127.0.0.1:2181";
    private final static CuratorFramework CLIENT;
    private static Integer baseSleepTimeMs = 500;//重试等待时间
    private static Integer maxRetries = 2;//最大重试次数
    private InterProcessMutex interProcessMutex;
    /**
     * 给当前线程一个 标识  只有获取锁成功的情况下  才支持解锁操作  见 unlock
     * 和  使用redis中实现分布式锁中的  SETNX  key value  中  当解锁时要判断当前解锁人是是不是获取锁了   的 value
     */
    private boolean hold = true;
    /** 模块 */
    private String modulName;
    static {
        //RetryPolicy  重试策略
        CLIENT = CuratorFrameworkFactory.newClient(zk_address,
                new ExponentialBackoffRetry(baseSleepTimeMs,maxRetries));
        CLIENT.start();
    }
    public DistributeLock(String modulName){
        this.modulName = modulName;
    }

    /**
     * 获取连接
     *
     * @return
     */
    private InterProcessMutex getInterProcessMutex() {
        return new InterProcessMutex(CLIENT, "/lock/" + this.modulName);
    }
    @Override
    public void lock() {
        try {
            interProcessMutex = getInterProcessMutex();
            interProcessMutex.acquire();
            System.out.println("tryLock=============success");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new RuntimeException("分布式锁不支持中断");
    }

    @Override
    public boolean tryLock() {
        try {
            interProcessMutex = getInterProcessMutex();
            boolean result = interProcessMutex.acquire(0, TimeUnit.SECONDS);
            if (!result) {
                hold = false;
            }else{
                System.out.println(Thread.currentThread().getName() + "tryLock=============success");
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            interProcessMutex = getInterProcessMutex();
            boolean result = interProcessMutex.acquire(time, unit);
            if (!result) {
                System.out.println(Thread.currentThread().getName() + "tryLock=============false");
                hold = false;
            }else{
                System.out.println(Thread.currentThread().getName() + "tryLock=============success");
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void unlock() {
        try {
            if (hold) {
                interProcessMutex.release();
                System.out.println(Thread.currentThread().getName() +  "unlock=============success");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
