package learn.forkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Fork/Join  用于执行并行任务的框架
 * 使用两个类来完成以上两个事情
 * 1.ForkJoinTask:我们使用ForkJoin 框架，必须首先创建一个ForkJoin任务，他提供在任务中执行fork()和join()操作机制。
 *   通常情况我们不需要直接继承ForkJoinTask类，只需要继承它的子类，ForkJoin提供了一下两个子类
 *   1.1 RecurseiveTask  用于有返回结果的任务
 *   1.2 RecursiveAction  用于没有返回结果的任务
 * 2 ForkJoinPoll:ForkJoinTask 需要ForkJoinPool来执行。
 *
 * 注：使用工作窃取算法
 */
public class CountTask extends RecursiveTask<Integer>{
    private static final int THRESHOLD = 2;//阈值
    private int start;
    private int end;
    public CountTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute){
            for (int i = start;i<=end;i++){

                sum += i;
            }
            try {
                TimeUnit.SECONDS.sleep(7);
            }catch (Exception e){

            }
        }else {
            //如果任务大于阈值，就分裂成两个任务计算
            int middle = (start + end ) / 2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle +1,end);
            leftTask.fork();
            rightTask.fork();
            //执行子任务
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
