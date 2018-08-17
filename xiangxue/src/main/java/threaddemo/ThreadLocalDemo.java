package threaddemo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 线程变量  ThreadLocalDemo 相关示例(用一句话描述该文件做什么)
 * @date
 */
public class ThreadLocalDemo {
    static ThreadLocal<Map<Object,Object>> localMap = new ThreadLocal<Map<Object,Object>>(){
        @Override
        protected Map<Object,Object> initialValue(){
            //return null;
            //如果不初始化这个变量，则下面使用的地方要进行判空
           return new HashMap<Object,Object>();
        }
    };

    public static class ThreadDemo implements Runnable{
        private int flag;
        public ThreadDemo(int num){
            this.flag = num;
        }
        @Override
        public void run(){
            System.out.println("my name is : " + Thread.currentThread().getName());
            localMap.get().put("Thread.currentThread().getName()",this.flag);
            System.out.println("my name is : " + Thread.currentThread().getName() + "localMap = " + localMap.get());
        }

    }

    public static void startThreeThread(){
        Thread [] threads = new Thread[3];
        int j = 0;
        for (int i=0;i<threads.length;i++) {
            threads[i] = new Thread(new ThreadDemo(j++));
        }
        for (Thread thread:threads) {
            thread.start();
        }
    }

    public static void main(String [] args){
        startThreeThread();
    }

}
