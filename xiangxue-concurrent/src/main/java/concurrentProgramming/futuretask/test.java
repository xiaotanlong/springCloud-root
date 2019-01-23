package concurrentProgramming.futuretask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: futureTask  test(用一句话描述该文件做什么)
 * @date 2018/12/1 14:11
 */
public class test {
    public static void main(String[] args) {
        WorkTask work = new WorkTask("FutureTask test");
        FutureTask<Integer> futureTask = new FutureTask(work);
        futureTask.run();

        try {
            int i = futureTask.get();
            System.out.printf("iiii"+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
class WorkTask implements Callable<Integer> {
    private String name;
    public WorkTask(String name) {
        this.name = name;
    }

    @Override
    public Integer call() {
        int sleepTime = new Random().nextInt(5000);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 返回给调用者的值
        return sleepTime;
    }
}