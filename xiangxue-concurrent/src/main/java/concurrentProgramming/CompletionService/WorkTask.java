package concurrentProgramming.CompletionService;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class WorkTask implements Callable<Integer> {
    private String name;
    public WorkTask(String name) {
        this.name = name;
    }

    @Override
    public Integer call() {
        int sleepTime = new Random().nextInt(1000);

        try {
            Thread.sleep(sleepTime * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 返回给调用者的值
        return sleepTime;
    }
}
