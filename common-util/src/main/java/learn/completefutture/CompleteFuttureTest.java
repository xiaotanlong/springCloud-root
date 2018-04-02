package learn.completefutture;

import java.util.concurrent.CompletableFuture;

/**
 * @author tjl
 * @version V1.0
 * @Title: completeFutture 类 学习
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date 2018-3-29
 */
public class CompleteFuttureTest {
    public static void main(String[] args) {
        CompletableFuture<Double> futurePrice = getPriceAsync();
        //do anything you want, 当前线程不被阻塞
        System.out.println(111);
        //线程任务完成的话，执行回调函数，不阻塞后续操作
        futurePrice.whenComplete((aDouble, throwable) -> {
            System.out.println(aDouble);
            System.out.println(throwable.getMessage());
            //do something else
        }).whenCompleteAsync((v, e) -> {
            System.out.println(v);
            System.out.println(e.getMessage());
        });


        System.out.println(222);

    }

    static CompletableFuture<Double> getPriceAsync() {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futurePrice.complete(11.01);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futurePrice.complete(21.01);
        }).start();

        return futurePrice;
    }
}
