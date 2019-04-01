package concurrentProgramming.threadPoolExecutorDemo;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: ScheduledThreadPoolExecutor，它可另行安排在给定的延迟后运行命令，或者定期执行命令。
 * 需要多个辅助线程时，或者要求 ThreadPoolExecutor 具有额外的灵活性或功能时，此类要优于Timer。
 *
 * executors Factory  该方式获取线程实例对象  也是 new 的形式
 * @date 2018-08-28
 */
public class ScheduledThreadPoolExecutorTest {
    // ScheduledThreadPoolExecutor 的两种实例化方法
    public static AtomicInteger c = new AtomicInteger(1);
    
    /**
     * 通过构造方法实例化
     * 0表示首次执行任务的延迟时间，
     * 40表示每次执行任务的间隔时间，
     * TimeUnit.MILLISECONDS执行的时间间隔数值单位
     */
    public static void constructionObject(){
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
        //这种方式 没执行一次会new 一个 Runnable对象
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("---构造方法进行实例化---" + c.getAndAdd(1));
            }
        }, 0, 4, TimeUnit.SECONDS);

        while (true){
            BlockingQueue<Runnable> bb =  scheduled.getQueue();
            System.out.println("---bb.size---" + bb.size());
            if (bb.size() > 0){
                if(c.get() > 5){
                    for (Runnable b : bb){
                        bb.remove(b);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                scheduled.shutdownNow();
                break;
            }
        }
    }

    /**
     * executors Factory获取实例对象
     */
    public static void executorsObject(){
        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    }

    public static void main(String [] args) throws Exception{
        constructionObject();
        /*String ss= "带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带个话带";
        System.out.println(ss.length());*/
    }
}
