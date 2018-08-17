package threaddemo;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 守护线程的机制(用一句话描述该文件做什么)
 * @date 2018.8.17
 *
 * 启动一个线程  如果把线程设置为守护线程，则该线程会和主线程同死（setDaemon(true)  要在线程启动之前才能生效）
 */
public class DeamonThread {
    public static class DeamonThreadRun extends Thread{
        int i = 90;
        @Override
        public void run() {
            while (i>1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeamonThreadRun name is " + Thread.currentThread().getName() + " new i is " + i--);
            }
        }
    }

    public static void main(String [] args) throws InterruptedException {
        DeamonThreadRun deamonThreadRun = new DeamonThreadRun();
        //deamonThreadRun.setDaemon(true);
        deamonThreadRun.start();
        Thread.sleep(5000);
        deamonThreadRun.interrupt();
    }
}
