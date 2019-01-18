package learn.threadcallback;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 定义回调方法模版
 * @date 2019/1/18 10:32
 */
public interface ICallbackMethods<T,R> {
    /**
     * 初始回调
     */
    void initCallbackMethod ();

    /**
     * 运行时回调
     */
    void runCallbackMethod ();

    /**
     *销毁时回调
     */
    void destroyCallbackMethod ();

    /**
     * 业务代码
     * @param data
     * @return
     */
    TaskResult<R> taskExecute(T data);
}
