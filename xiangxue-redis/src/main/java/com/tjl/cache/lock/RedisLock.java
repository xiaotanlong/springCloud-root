package com.tjl.cache.lock;

import com.tjl.cache.basic.Basic;
import com.tjl.cache.utils.FileUtils;
import com.tjl.cache.utils.JedisUtils;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: RedisLock  redis 分布式锁
 * @date 2019/1/24 14:33
 */
public class RedisLock implements Lock {

    private static AtomicInteger intn = new AtomicInteger(0);
    private static final String  KEY = "LOCK_KEY";

    JedisUtils jedis = new JedisUtils(Basic.ip, Basic.port, Basic.auth);

    private ThreadLocal<String> local = new ThreadLocal<>();


    @Override
    //阻塞式的加锁
    public void lock() {
        //1.尝试加锁
        if(tryLock()){
            System.out.println(Thread.currentThread().getName() + "tryLock success .total is :" + intn.decrementAndGet());
            return;
        }
        //2.加锁失败，当前任务休眠一段时间
        try {
            Thread.sleep(10);//性能浪费
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        //3.递归调用，再次去抢锁
        lock();
    }



    @Override
    //阻塞式加锁,使用setNx命令返回OK的加锁成功，并生产随机值
    public boolean tryLock() {
        //产生随机值，标识本次锁编号
        String uuid = UUID.randomUUID().toString();
        /**
         * key:我们使用key来当锁
         * uuid:唯一标识，这个锁是我加的，属于我
         * NX：设入模式【SET_IF_NOT_EXIST】--仅当key不存在时，本语句的值才设入
         * PX：给key加有效期
         * 1000：有效时间为 1 秒
         */
        String ret = jedis.set(KEY, uuid,"NX","PX",1000l);
        //设值成功--抢到了锁
        if("OK".equals(ret)){
            local.set(uuid);//抢锁成功，把锁标识号记录入本线程--- Threadlocal
            return true;
        }

        //key值里面有了，我的uuid未能设入进去，抢锁失败
        return false;
    }

    //错误解锁方式
    public void unlockWrong() {
        //获取redis的原始连接
        String uuid = jedis.get(KEY);//现在锁还是自己的

        //uuid与我的相等，证明这是我当初加上的锁
        if (null != uuid && uuid.equals(local.get())){//现在锁还是自己的
            //锁失效了

            //删锁
            jedis.del(KEY);
        }
    }

    //正确解锁方式
    public void unlock() {
        //读取lua脚本
        String script = FileUtils.getScript("lua/unlock.lua");
        //通过原始连接连接redis执行lua脚本
        jedis.eval(script,Arrays.asList(KEY), Arrays.asList(local.get()));
    }

    //-----------------------------------------------

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 参考
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit)
            throws InterruptedException {
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new RuntimeException("不支持中断");
    }

}
