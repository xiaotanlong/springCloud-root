package learn.threadcallback;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 存储当前提交过来的信息任务信息
 * @date 2019/1/18 11:08
 */
public class JobInfo<R> {
    //区分唯一的工作
    private final String jobName;
    //这个工作的任务处理器
    private final ICallbackMethods<?,?> callbackMethods;
    //结果保存时间
    private final long expireTime;
    /**
     * 业务结果
     */
    private TaskResult<R> result;

    public JobInfo(String jobName,
                   ICallbackMethods<?, ?> callbackMethods,
                   long expireTime) {
        super();
        this.jobName = jobName;
        this.callbackMethods = callbackMethods;
        this.expireTime = expireTime;
    }
    public String getJobName() {
        return jobName;
    }

    public ICallbackMethods<?, ?> getCallbackMethods() {
        return callbackMethods;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public TaskResult<R> getResult() {
        return result;
    }

    public void setResult(TaskResult<R> result) {
        this.result = result;
    }
}
