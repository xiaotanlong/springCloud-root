package threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 创建线程例子
 * @date 2018.8.16
 *
 * 怎么让线程安全停止
 *
 *
 * Thread.currentThread()  获取当前线程
 * InterruptedException 会重置线程中断标识位  捕获该异常后要手动设置中断标识位（
 *              线程的休眠，让步等方法 会抛出 InterruptedException）
 *
 */
public class NewThread {

    //实现 Runnable接口
    private  static class UseRun implements Runnable{
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Thread isInterrupted is :" + Thread.currentThread().isInterrupted() );
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    //在异常中执行打断   才能中断线程    因为 InterruptedException 会重置线程中断标识位
                    Thread.currentThread().interrupt();
                    System.out.println("----UseRun implements Runnable =   is error ");
                    e.printStackTrace();
                }
                finally {//如果是  守护线程  则finally块不能保证执行
                    System.out.println("----UseRun finally is run ");
                }
                System.out.println("Thread name is :" + name );

            }
            System.out.println("----UseRun isInterrupted :" + Thread.currentThread().isInterrupted());
        }
    }

    /**
     * 实现 Callable
     * Callable 支持有返回值
     */
    private static class UseCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("---------UseCall implements Callable =========");
            return "CallResurt";
        }
    }

    public static void main(String [] args) throws ExecutionException, InterruptedException {
        UseRun useRun = new UseRun();
        Thread thread = new Thread(useRun);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();

        /*UseCall useCall = new  UseCall();
        FutureTask<String> futureTask = new FutureTask<String>(useCall);
        Thread test = new Thread(futureTask);
        test.start();*/


       /* System.out.println("======"+futureTask.get());*/
    }
}

