package learn.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class WorkThread extends Thread {

    private Runnable target;
    private AtomicInteger counter;

    public WorkThread(Runnable target, AtomicInteger counter) {
        this.target = target;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            System.out.println(target.hashCode());
            target.run();

        } finally {
            int c = counter.getAndDecrement();
            System.out.println("terminate no " + c + " Threads");
        }
    }
}
