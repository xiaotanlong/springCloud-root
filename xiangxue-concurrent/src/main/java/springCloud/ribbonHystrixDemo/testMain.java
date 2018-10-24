package springCloud.ribbonHystrixDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 测试启动类
 * @date
 * 1.模拟接口调用
 * 2.给接口加一个 HystrixCommand 控制类
 */
public class testMain {
    private static CountDownLatch count = new CountDownLatch(80);
    public static void main(String [] args) throws InterruptedException{
        for (int i=0 ;i < 6 ;i++){
            new WorkerThred(i).start();
        }

        Thread.sleep(10 * 1000);

        for (int i=10 ;i < 15 ;i++){
            new WorkerThred(i).start();
        }

        Thread.sleep(10 * 1000);

        /*for (int i=20 ;i < 25 ;i++){
            new WorkerThred(i).start();
        }*/
        //count.await();
        System.out.println("main is end");
    }

    public static class WorkerThred extends Thread{
        private int n;
        public WorkerThred(int n){
            this.n = n;
        }
        @Override
        public void run() {
            int j = n * 10;
            for (int i=0 ;i < 10 ;i++){
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread getIntefaceThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String ss = null;
                        //ss = InterfaceProvidederTest.getMsg();
                        /*Integer parm = (int)(Math.random()*9);
                        try {
                            ss = InterfaceProvidederTest.getMsgSleep(parm);
                        }catch (Exception e){

                        }*/


                        try {
                            ss = HystrixCommandTest1.callInterface();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        System.out.println(ss);
                        count.countDown();
                    }
                });
                getIntefaceThread.setName("getIntefaceThread-" + j++);
                getIntefaceThread.start();
            }
        }
    }
}
