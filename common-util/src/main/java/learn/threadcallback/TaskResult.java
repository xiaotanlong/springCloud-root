package learn.threadcallback;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 实际执行的任务
 * @date 2019/1/18 10:45
 */
public class TaskResult<R> {
    //方法本身运行是否正确的结果类型
    private final TaskResultType resultType;
    //方法的业务结果数据；
    private R returnValue;
    //这里放方法失败的原因
    private final String reason;

    public TaskResult(TaskResultType resultType, R returnValue, String reason) {
        super();
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = reason;
    }

    public TaskResult(TaskResultType resultType, R returnValue) {
        super();
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = "Success";
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getReturnValue() {
        return returnValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReturnValue(R returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String toString() {
        return "TaskResult [resultType=" + resultType
                + ", returnValue=" + returnValue
                + ", reason=" + reason + "]";
    }



}
