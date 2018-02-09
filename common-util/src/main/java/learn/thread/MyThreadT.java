package learn.thread;

public class MyThreadT extends Thread {

    public void run() {
        System.out.println("befo interrupt ------------");
        this.interrupt();
        super.interrupt();
        System.out.println("after interrupt ------------");
    }
}
