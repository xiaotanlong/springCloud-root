package learn.forkjoin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class mainTest {
    public static void main(String [] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        //生成一个任务，设置计算起始位置
        CountTask countTask = new CountTask(1,8);
        System.out.println("start date : " +dateFormat.format(new Date()));
        //执行任务
        Future<Integer> future = forkJoinPool.submit(countTask);
        try {
            System.out.println(future.get());

        }catch (Exception e){

        }
        System.out.println("start date : " +dateFormat.format(new Date()));
    }
}
