package learn.threadcallback.demo;

import learn.threadcallback.ICallbackMethods;
import learn.threadcallback.TaskResult;
import learn.threadcallback.TaskResultType;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 具体业务应用
 * @date 2019/1/18 11:42
 */
public class AppTask implements ICallbackMethods<String,String> {
    @Override
    public void initCallbackMethod() {
        System.out.println("AppTask ----------   初始回调");
    }

    @Override
    public void runCallbackMethod() {
        //可以另起线程  ，或者  发mQ消息  ，
        System.out.println("AppTask ----------   执行回调");
    }

    @Override
    public void destroyCallbackMethod() {
        System.out.println("AppTask ----------   销毁回调");
    }

    @Override
    public TaskResult<String> taskExecute(String data) {
        System.out.println("AppTask ----------   业务执行" + data);

        return new TaskResult(TaskResultType.Success,"AppTask ----------   业务执行 - success" + data);
    }
}
