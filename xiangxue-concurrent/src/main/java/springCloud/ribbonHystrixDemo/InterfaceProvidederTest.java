package springCloud.ribbonHystrixDemo;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 被调用的接口类
 * @date
 */
public class InterfaceProvidederTest {
    //直接调用
    public static String getMsg(){
        return "getMsg----我被线程：" +Thread.currentThread().getName()+ "调用了";
    }

    //随机休眠时间
    public static String getMsgRandomSleep(Integer num,String threadName) throws InterruptedException {
        Integer waitTime = num % 20;
        Thread.sleep(waitTime * 1000);
        return "getMsgRandomSleep----我被线程：" + threadName + "调用了";
    }

    //固定休眠时间
    public static String getMsgSleep(Integer num) throws InterruptedException {
        Thread.sleep(num * 1000);
        return "getMsgSleep-----我被线程：" +Thread.currentThread().getName()+ "调用了";
    }
}
