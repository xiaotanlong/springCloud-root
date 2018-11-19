package concurrentProgramming.threaddemo;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class test {
    public static void main(String [] args) throws InterruptedException {
        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行----");
            }
        });
        System.out.println("main name=====" + Thread.currentThread().getName());
        System.out.println("test name=====" + test.getName());
        System.out.println("time is =====" + System.currentTimeMillis());
        test.start();
        Thread o = test;
        o.sleep(5000);
        System.out.println("test name=====" + test.getName());
        System.out.println("time is =====" + System.currentTimeMillis());
        System.out.println("休眠后----end");
    }
}
