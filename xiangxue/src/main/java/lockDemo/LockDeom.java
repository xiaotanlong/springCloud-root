package lockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 显示锁的demo
 * @date
 */
public class LockDeom {
    private Lock lock = new ReentrantLock();
    int a = 0;
    public void add(){
        lock.lock();
        try{
            a++;
        }finally {
            lock.unlock();
        }

    }

    public synchronized void add2(){
            a++;

    }
}
