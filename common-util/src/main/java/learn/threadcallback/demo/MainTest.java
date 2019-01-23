package learn.threadcallback.demo;

import learn.threadcallback.AbstractCallbackMethodsThread;
import learn.threadcallback.PoolUtil;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 测试回调
 * @date 2019/1/18 11:59
 */
public class MainTest {
    public static void main(String[] args) {
        /*AppTask appTask = new AppTask();
        //拿到框架的实例
        PoolUtil pool = PoolUtil.getInstance();
        //注册job
        pool.registerJob("mytest",  appTask,1000*5,"dasdklasjdlk");*/
        //pool.putTask("mytest","dasdklasjdlk");

        //模版的方式
        Thread ss = new Thread(new MyCallbackMethodsThread("模版方式测试---------"));
        ss.start();
    }
}
