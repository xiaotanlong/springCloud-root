package learn.threadcallback;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 带有回调的thread
 * @date 2019/1/19 13:08
 */
public abstract class AbstractCallbackMethodsThread<R,T>  implements Runnable{
    private T data;
    public AbstractCallbackMethodsThread(T data){
        this.data = data;
    }
     /*** 初始回调*/
     protected  abstract void initCallbackMethod ();
    /*** 运行时回调*/
    protected  abstract void runCallbackMethod ();
    /***销毁时回调*/
    protected  abstract void destroyCallbackMethod ();
    /*** 业务方法* @param data* @return*/
    protected  abstract R taskExecute(T data);
    @Override
    public void run() {
        try{
            runCallbackMethod ();
            taskExecute(data);
        }catch (Exception e){

        }finally {
            destroyCallbackMethod ();
        }
        initCallbackMethod ();
    }
}
