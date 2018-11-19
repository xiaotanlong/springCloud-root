package concurrentProgramming.threadPoolExecutorDemo;

import java.util.concurrent.*;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description:线程池基本使用
 */
public class MainTest {
    public void main(String [] args){
        ExecutorService pool = Executors.newFixedThreadPool(5);
        /**
         * 核心线程数，最大线程数，空闲销毁时间，空闲销毁时间单位，队列，饱和策略
         */
        ExecutorService pool2 = new ThreadPoolExecutor(1,1,60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),new ThreadPoolExecutor.AbortPolicy());


        pool2.execute(null);//不要返回值
        //pool2.submit(null);//可以接受返回值--使用calable
        pool2.shutdown();//关闭线程池
    }
}
