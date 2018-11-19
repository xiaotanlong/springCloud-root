package concurrentProgramming.threaddemo;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: wait notify 原理，性能测试
 * wait 和 notify/notifyAll是类的方法   在有线程安全标示（锁 ，synchronized）
 * @date 2018/8/20
 */
public class ThreadWaitAndNotify {

        public synchronized void waitTest(int m) throws InterruptedException {
            int i = 0;
            while (i < 10){
                System.out.println(Thread.currentThread().getName() + "begin----i:" + i);
                if(i == m){
                    this.wait();
                }
                i++;
                System.out.println(Thread.currentThread().getName() + "end----i:" + i);
            }

            System.out.println(Thread.currentThread().getName() + "over----i:" + i);
        }

        public synchronized void notfyTest() throws InterruptedException{
            System.out.println(Thread.currentThread().getName() + "====notfyTest" );
            notify();
        }

        public synchronized void notfyAllTest()throws InterruptedException{
            System.out.println(Thread.currentThread().getName() + "====notfyTestAll" );
            notifyAll();
        }


    public static void main(String [] args) throws InterruptedException {
        final ThreadWaitAndNotify waitAndNotify = new ThreadWaitAndNotify();
        for (int i = 0;i< 5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitAndNotify.waitTest(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(2000l);

        //waitAndNotify.notfyTest();
        waitAndNotify.notfyAllTest();

    }
}
