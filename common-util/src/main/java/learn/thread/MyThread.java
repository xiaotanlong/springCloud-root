package learn.thread;

public class MyThread implements Runnable {


    public void run(){
        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("complete a task!!!");
    }
}
