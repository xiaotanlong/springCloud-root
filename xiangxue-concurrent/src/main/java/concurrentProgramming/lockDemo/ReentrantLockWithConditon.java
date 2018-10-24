package concurrentProgramming.lockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: ReentrantLock 配合 Conditond 使用
 * @date 2018/10/17 19:13
 */
public class ReentrantLockWithConditon implements Runnable{
    public static ReentrantLock lock = new ReentrantLock(true);
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.newCondition();
        try {
            lock.lock();
            System.err.println(Thread.currentThread().getName() + "-线程开始等待...");
            condition.await();
            System.err.println(Thread.currentThread().getName() + "-线程继续进行了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        try {
            lock.lock();
            System.err.println(Thread.currentThread().getName() + "-线程开始等待2...");
            condition.await();
            System.err.println(Thread.currentThread().getName() + "-线程继续进行了2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockWithConditon test = new ReentrantLockWithConditon();
        Thread t = new Thread(test, "线程ABC");
        t.start();
        Thread.sleep(1000);
        System.err.println("过了1秒后...");
        lock.lock();
        condition.signalAll(); // 调用该方法前需要获取到创建该对象的锁否则会产生
        // java.lang.IllegalMonitorStateException异常
        lock.unlock();

        Thread.sleep(5000);
        System.err.println("过了1秒后...");
        lock.lock();
        condition.signal(); // 调用该方法前需要获取到创建该对象的锁否则会产生
        // java.lang.IllegalMonitorStateException异常
        lock.unlock();
    }
}
