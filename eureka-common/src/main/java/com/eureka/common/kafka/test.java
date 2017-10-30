package com.eureka.common.kafka;

/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.common.kafka
 * @Author tanjianglong
 * @CreatedTime 2017/10/25.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class test {
    public static void main(String[] args) {
        /*int d = 39;
        int a = 40;
        int c = 41;
        System.out.println(String.valueOf(d).hashCode());
        System.out.println(String.valueOf(a).hashCode());
        System.out.println(String.valueOf(c).hashCode());*/

        Thread t1 = new MyThread1();
        //Thread t2 = new Thread(new MyRunnable());
        t1.setPriority(10);
        //t2.setPriority(1);
        t1.start();




        System.out.println("main"  + "次执行！");
    }


}
class MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程1第" + i + "次执行！");
            if(i == 5)
            {
                Thread t2 = new Thread(new MyRunnable());
                t2.start();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程2第" + i + "次执行！");
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }
    }
}
