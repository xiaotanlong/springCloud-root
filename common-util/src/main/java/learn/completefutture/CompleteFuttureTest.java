package learn.completefutture;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author tjl
 * @version V1.0
 * @Title: completeFutture 类 学习
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date 2018-3-29
 */
public class CompleteFuttureTest {
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
    /**
     * Future 监测子线程执行结束
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void FutureTest() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(() -> {
            // 长时间的异步计算
            // ...
            //6.使主任务睡眠2秒
            try {
                //使用timeUnit类将参数单位设置为秒
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 然后返回结果
            return 100;
        });
        System.out.printf("Main:Canceled:%s\n",f.isCancelled());
        System.out.printf("Main:Done:%s\n",f.isDone());
        //f.cancel(true);
        //这里使用get方法会阻塞线程  但是后面要手动结束线程执行
        System.out.println(f.get());
        System.out.printf("Main:Done:%s\n",f.isDone());
        if(f.isDone()){
            es.shutdown();
        }
    }

    /**
     * future.join  future.get 异常比较
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void CompletableFutureTest0() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });
        //future.join();
        future.get();
    }

    /**
     * 子线程按步骤执行  f.complete  启动控制
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     * 有时你想要看到信号发生故障的情况，如你所知Future对象可以处理它所包含的结果或异常。
     * 如果你想进一步传递一些异常，可以用CompletableFuture.completeExceptionally(ex)
     * (或者用obtrudeException(ex)这样更强大的方法覆盖前面的异常)。
     * completeExceptionally()也能解锁所有等待的客户端，但这一次从get()抛出异常。说到get()，
     * 也有CompletableFuture.join()方法在错误处理方面有着细微的变动。
     * 但总体上，它们都是一样的。
     * 最后也有CompletableFuture.getNow(valueIfAbsent)方法没有阻塞但是如果Future还没完成将返回默认值，
     * 这使得当构建那种我们不想等太久的健壮系统时非常有用。
     * 最后static的方法是用completedFuture(value)来返回已经完成Future的对象，当测试或者写一些适配器层时可能非常有用。
     */
    public  void CompletableFutureTest() throws ExecutionException, InterruptedException, IOException {
        final CompletableFuture<Integer> f = compute();
        //new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        /**通常futures代表其它线程中运行的代码，
         * 但事实并非总是如此。有时你想要创造一个Future来表示你知道将会发生什么，例如JMS message arrival。
         * 所以你有Future但是未来并没有潜在的异步工作。你只是想在未来JMS消息到达时简单地完成（解决），
         * 这是由一个事件驱动的。在这种情况下，你可以简单地创建CompletableFuture来返还给你的客户端，
         * 只要你认为你的结果是可用的，仅仅通过complete()就能解锁所有等待Future的客户端。
         *
         * 理解为 通知 事件执行
         */

        //f.completeExceptionally(new Exception("1111111111111111"));
        //System.in.read();

        /**
         * 上一步完成 可执行下一步
         */
        f.whenComplete((aDouble, throwable) -> {
            System.out.println(aDouble + "====");
            //TODO
            //next
            final CompletableFuture<Integer> f2 = compute();
            new Client2("Client1", f2).start();
            //new Client("Client2", f2).start();
            System.out.println("waiting2");
            f2.complete(101);
            f2.whenComplete((aDouble2,throwable2) -> {
                System.out.println(aDouble2 + "====222");
                //TODO
                //next
            });
        });
    }
    public static void main(String[] args) {
        try{
            //FutureTest();
            new CompleteFuttureTest().CompletableFutureTest();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    class Client extends Thread {
        CompletableFuture<Integer> f;
        Client(String threadName, CompletableFuture<Integer> f) {
            super(threadName);
            this.f = f;
        }
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                f.complete(100);
                System.out.println(this.getName() + ": " + f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    class Client2 extends Thread {
        CompletableFuture<Integer> f;
        Client2(String threadName, CompletableFuture<Integer> f) {
            super(threadName);
            this.f = f;
        }
        @Override
        public void run() {
            try {
                System.out.println(this.getName() + ": " + f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
