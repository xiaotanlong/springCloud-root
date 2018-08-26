package concurrentProgramming.ConnectionPoolDemo;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 连接词测试类   启动多个线程测试数据库连接测试   同时测试wite  和  notify/notifyall方法
 * @date 2018.8.20
 */
public class ConnectionPoolTest {
    //初始化一个连接池--默认长度为10
    static ConnectionPool connectionPool = new ConnectionPool(10);
    // 线程工具类---控制器:控制main线程将会等待所有Woker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String [] args) throws InterruptedException {
        int threadNumbew = 20;
        AtomicInteger got = new AtomicInteger();//获取到连接的次数
        AtomicInteger notGot = new AtomicInteger();//没有获取到连接的次数
        int count = 10;//每个线程的操作次数
        end = new CountDownLatch(threadNumbew);
        for (int i = 0 ;i< threadNumbew ;i++){
            new Thread(new Worker(count,got,notGot)).start();
        }
        end.await();

        System.out.println("总共尝试了: " + (threadNumbew * count));
        System.out.println("拿到连接的次数：  " + got);
        System.out.println("没能连接的次数： " + notGot);
    }

    public static class Worker implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count,AtomicInteger got,AtomicInteger notGot){
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        @Override
        public void run() {
            int i = 0;
            while (i < count){
                try {
                    Connection connection = connectionPool.getCoon(1000l);
                    if(connection != null){
                        try {
                            System.out.println(Thread.currentThread().getName() + " 获取到连接");
                            //connection.createStatement();
                            connection.commit();
                        }finally {
                            got.incrementAndGet();
                            connectionPool.releaseConn(connection);
                        }
                    }else{
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + " 等待超时！！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    count --;
                }
            }
            end.countDown();
        }
    }
}
