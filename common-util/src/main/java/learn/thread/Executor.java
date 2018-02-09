package learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *线程工具类
 * 线程池设置最大核心线程数后，该线程池中只会创建该数量的线程，提交到该线程池中的线程只会在这些创建的线程中执行，
 * 当核心线程都在执行任务时，则新提交的线程将处于排队队列中。
 */
public class Executor {

    ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        ExecutorService ctp =  Executors.newFixedThreadPool (8,new MyThreadFactory() );

        MyThread my1 = new MyThread();
        MyThread my2 = new MyThread();
        MyThread my3 = new MyThread();
        MyThread my4 = new MyThread();
        MyThread my5 = new MyThread();
        MyThread my6 = new MyThread();
        MyThreadT my7 = new MyThreadT();
        final  Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    this.notifyAll();
                    System.out.println("-----1111111111111");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        final  Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    th.join();
                    System.out.println("-----22222222");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final  Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    th.interrupt();
                    System.out.println("-----22222222");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
        th2.start();
        th3.start();

        /*ctp.execute(my1);
        ctp.execute(my2);
        ctp.execute(my3);
        ctp.execute(my4);
        ctp.execute(my5);
        ctp.execute(my6);*/
        //ctp.execute(my7);

        //暂停提交线程 该线程池不再执行新提交的任务，已经提交的会执行完成
        ctp.shutdown();
        //ctp.shutdownNow();//结束所有线程  包括正在执行的线程

        try {
            System.out.println(ctp.awaitTermination(5, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
