package concurrentProgramming.threaddemo;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 线程的run和startDe分别(用一句话描述该文件做什么)
 * @date 2018.8.16
 */
public class ThreadRunOrStart {
    public static class StartAndRun extends Thread{
        int i = 90;
        @Override
        public void run() {
            while (i>1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread name is " + Thread.currentThread().getName() + " new i is " + i--);
            }
        }
    }

    /**
     * run  he  start  的区别
     * run 相当于在当前线程中执行 run方法
     * start 相当于开启另一个线程，交给cpu调度
     * 线程的栈帧，内存空间
     * @param args
     */
    public static void main(String[] args){
        StartAndRun startAndRun = new StartAndRun();
        startAndRun.start();
        startAndRun.run();
    }
}
