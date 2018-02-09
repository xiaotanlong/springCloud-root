package learn.aliyun;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Worker
 *
 */
public class Worker {

    static AtomicInteger counter = new AtomicInteger(0);

    public void work(){
        //以原子方式将当前值加 1。 新值
        counter.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + " work ");
    }

    public static int getCount(){
        return counter.get();
    }

}
