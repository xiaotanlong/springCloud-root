package learn.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger count = new AtomicInteger();
    public Thread newThread(Runnable r) {

        int c = count.incrementAndGet();
        System.out.println("create no " + c + " Threads");
        return new WorkThread(r,count);
    }
}
