package learn.threadcallback.demo;

import learn.threadcallback.PoolUtil;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 测试回调
 * @date 2019/1/18 11:59
 */
public class MainTest {
    public static void main(String[] args) {
        AppTask appTask = new AppTask();
        //拿到框架的实例
        PoolUtil pool = PoolUtil.getInstance();
        //注册job
        pool.registerJob("mytest",  appTask,1000*5,"dasdklasjdlk");
        //pool.putTask("mytest","dasdklasjdlk");
    }
}
