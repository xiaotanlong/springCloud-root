package concurrentProgramming.deadlock;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description:
 * 普通的==
 * 死锁：演示普通的死锁和解决
 * 解决：获取锁的顺序一致
 * 怀疑发送死锁：
     通过jps 查询应用的 id，
     再通过jstack id 查看应用的锁的持有情况
     解决办法：保证加锁的顺序性
 *
 * 动态的：锁的是参数，看似顺序一致，但是由于参数传递的顺序不能保证，也有可能导致锁的顺序一致
 * @date 2018/10/12 11:21
 */
public class NormalDeadLock {
    private static Object valueFirst = new Object();//第一个锁
    private static Object valueSecond = new Object();//第二个锁

    //先拿第一个锁，再拿第二个锁
    private static void fisrtToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueFirst) {
            System.out.println(threadName+" get first");
            Thread.sleep(300);
            synchronized (valueSecond) {
                System.out.println(threadName+" get second");
            }
        }
    }

    //先拿第二个锁，再拿第一个锁---死锁
    //先拿第一个锁，再拿第二个锁---解决
    private static void SecondToFisrt() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueFirst) {
            System.out.println(threadName+"get first ");
            Thread.sleep(300);
            synchronized (valueSecond) {
                System.out.println(threadName+"get second ");
            }
        }
    }

    //执行先拿第二个锁，再拿第一个锁
    private static class TestThread extends Thread{

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run(){
            Thread.currentThread().setName(name);
            try {
                SecondToFisrt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDeadLock");
        TestThread testThread = new TestThread("SubTestThread");
        testThread.start();
        try {
            fisrtToSecond();//先拿第一个锁，再拿第二个锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
