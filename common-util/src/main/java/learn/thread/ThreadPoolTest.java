package learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class ThreadPoolTest {
    public static ExecutorService ctp =  Executors.newFixedThreadPool(3);
    public static void main(String[] args) {
        for(int i = 1; i < 100 ; i++){
            ctp.submit(new Runnable() {
                @Override
                public void run() {
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch (Exception e ){

                    }
                    System.out.println("iiiiiii:" + this.getClass());
                }
            });
        }
    }
}
