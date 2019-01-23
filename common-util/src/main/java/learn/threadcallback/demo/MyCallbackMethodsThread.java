package learn.threadcallback.demo;

import learn.threadcallback.AbstractCallbackMethodsThread;

/**
 * @author 0217319
 * @version V1.0
 * @Description:
 * @date 2019/1/19 13:21
 */
public class MyCallbackMethodsThread extends AbstractCallbackMethodsThread<String,String> {
    public MyCallbackMethodsThread(String data) {
        super(data);
    }
    @Override
    protected void initCallbackMethod() {
        System.out.println("initCallbackMethod 。。。。。。。。初始化");
    }
    @Override
    protected void runCallbackMethod() {
        System.out.println("runCallbackMethod 。。。。。。。。执行");
    }
    @Override
    protected void destroyCallbackMethod() {
        System.out.println("destroyCallbackMethod 。。。。。。。。销毁");
    }
    @Override
    protected String taskExecute(String data) {
        System.out.println("taskExecute 。。。。。。。。" + data);
        return "taskExecute 。。。。。。。。" + data;
    }
}
