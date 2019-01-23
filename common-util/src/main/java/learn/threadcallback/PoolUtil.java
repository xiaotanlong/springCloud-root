package learn.threadcallback;

import java.util.concurrent.*;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 统一接受任务执行
 * @date 2019/1/18 10:50
 */
public class PoolUtil {
    //核心
    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();
    //任务队列
    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);
    //线程池，固定大小，有界队列
    private static ExecutorService taskExecutor =
            new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60,
                    TimeUnit.SECONDS, taskQueue);

    /**
     * 任务结果保存缓存器   一定时间内  查询异步执行的结果
     */
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap
            = new  ConcurrentHashMap<>();

    private PoolUtil() {}

    private static class PoolHolder{
        public static PoolUtil pool = new PoolUtil();
    }

    /**
     * 单例--返回线程池
     * @return
     */
    public static PoolUtil getInstance() {
        return PoolHolder.pool;
    }

    /**
     * 线程统一封装
     * @param <T>
     * @param <R>
     */
    private static class RunnableTask<T,R> implements Runnable{

        private JobInfo jobInfo;
        private T data;
        private ICallbackMethods<T,R> callbackMethods;

        public RunnableTask(JobInfo jobInfo,T data){
            super();
            this.jobInfo = jobInfo;
            this.data = data;
            this.callbackMethods = (ICallbackMethods<T, R>)jobInfo.getCallbackMethods();
            //调用初始化 具体回调方式   可以自定义  线程  mQ 都可以  改动不影响  逻辑
            this.callbackMethods.initCallbackMethod();
        }
        @Override
        public void run() {
            R r = null;
            //运行时回调   具体回调方式   可以自定义  线程  mQ 都可以  也可以让具体业务自己实现
            try{
                this.callbackMethods.runCallbackMethod();
            }catch (Exception e){

            }

            TaskResult<R> result = null;
            try {
                //调用业务人员实现的具体方法
                result = this.callbackMethods.taskExecute(data);

                Thread.sleep(5000);

                if (result == null) {
                    result = new TaskResult<R>(TaskResultType.Exception, r,
                            "result is null");
                }
                if (result.getResultType() == null) {
                    if (result.getReason() == null) {
                        result = new TaskResult<R>(TaskResultType.Exception, r, "reason is null");
                    } else {
                        result = new TaskResult<R>(TaskResultType.Exception, r,
                                "result is null,but reason:" + result.getReason());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new TaskResult<R>(TaskResultType.Exception, r,
                        e.getMessage());
            }finally {
                this.jobInfo.setResult(result);
                jobInfoMap.put(jobInfo.getJobName(),jobInfo);
                //销毁是回调
                this.callbackMethods.destroyCallbackMethod();
            }

        }
    }


    /*private <R> JobInfo<R> getJob(String jobName){
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if(null==jobInfo) {
            throw new RuntimeException(jobName+"已存在。");
        }
        return jobInfo;
    }

    public <T,R> void putTask(String jobName,T t) {
        JobInfo<R> jobInfo = getJob(jobName);
        RunnableTask<T,R> task = new RunnableTask<T,R>(jobInfo,t);
        taskExecutor.execute(task);
    }*/

    public <T,R> void registerJob(String jobName,
                                ICallbackMethods<?, ?> taskProcesser,long expireTime,T data) {
        JobInfo<R> jobInfo = new JobInfo(jobName,
                taskProcesser,expireTime);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo)!=null) {
            throw new RuntimeException(jobName+"已经注册了！");
        }
        RunnableTask<T,R> task = new RunnableTask<T,R>(jobInfo,data);
        taskExecutor.execute(task);
    }
    //启动线程清楚 过期 job
}
