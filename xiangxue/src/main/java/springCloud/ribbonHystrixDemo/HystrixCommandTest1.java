package springCloud.ribbonHystrixDemo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 0217319
 * @version V1.0
 * @Description: Hystrix Command 命令类
 * 1.使用场景   必须要同步的 ，调用容易出错，不是整个逻辑必须的    接口
 * 2.降级（主逻辑失败采用备用逻辑的过程）。
 * 3.熔断（常用接口因短期内多次失败，而被暂时性的忽略，不再尝试使用）。
 * @date
 */
public class HystrixCommandTest1 {
    private static Semaphore semaphore = new Semaphore(100);//最大并发数
    private static volatile int hystrixStatus = 1;//1：关闭   2：打开  3：半开
    private static AtomicBoolean onHalfOpenIsPassed = new AtomicBoolean(false);

    private static Long overTime = 5 * 1000L;//接口超时时间
    private static Long HystrixFuseSpacingTime = 5 * 1000L;//熔断间隔时间
    private static volatile Long HystrixFuseTime = System.currentTimeMillis();//熔断时时间

    private static Map<Long,String> Hystrixdata = new ConcurrentHashMap();

    static {
        new Thread(new CheckIsFusibleWork()).start();
    }

    //调用
    public static String callInterface() throws InterruptedException, ExecutionException {
        if(semaphore.availablePermits() > 0 && !(hystrixStatus == 2)){
            Integer parm = (int)(Math.random()*9);
            //半开状态处理
            if(hystrixStatus == 3){
                if(onHalfOpenIsPassed.get()){
                    return getFallback() + "半开状态--一个通过已经执行";
                }
                System.out.println("半开状态-----------执行" + Thread.currentThread().getName() + " hystrixStatus is :"+ hystrixStatus);
                semaphore.acquire();
                FutureTask<String> work = new FutureTask<String>(new Work(parm));
                new Thread(work).start();
                String resurt = "";
                onHalfOpenIsPassed.set(true);
                try {
                    resurt = work.get(overTime, TimeUnit.MILLISECONDS);
                    hystrixStatus = 1;
                    System.out.println("半开状态->关闭" + Thread.currentThread().getName() + ":调用接口成功----parm is :"+ parm );
                } catch (TimeoutException e) {
                    //e.printStackTrace();
                    System.out.println("*****半开状态->打开" + Thread.currentThread().getName() + ":调用接口超时----parm is :"+ parm );
                    resurt = getFallback();
                    //Hystrixdata.put(System.currentTimeMillis(),resurt);
                    hystrixStatus = 2;
                    HystrixFuseTime = System.currentTimeMillis();
                    //调起熔断回复
                    startFusibleRepairWork();
                }finally {
                    semaphore.release();
                }
                return resurt;
            }else{//关闭状态处理
                System.out.println("关闭状态-----------执行" + Thread.currentThread().getName() + " hystrixStatus is :"+ hystrixStatus);
                semaphore.acquire();
                FutureTask<String> work = new FutureTask<String>(new Work(parm));
                new Thread(work).start();
                String resurt = "";
                try {
                    resurt = work.get(overTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    //e.printStackTrace();
                    System.out.println("*****关闭状态---" + Thread.currentThread().getName() + ":调用接口超时----parm is :"+ parm + " hystrixStatus is :"+ hystrixStatus);
                    resurt = getFallback();
                    Hystrixdata.put(System.currentTimeMillis(),resurt);
                }finally {
                    semaphore.release();
                }
                return resurt;
            }
        }else{
            if(semaphore.availablePermits() <= 0){
                return getFallback() + "最大线程数不足";
            }else{
                return getFallback() + "hystrixStatus is 2 --";
            }

        }

    }

    public static String getFallback(){
        return Thread.currentThread().getName() + "-线程  is use getFallback---";
    }

    public static synchronized void startFusibleRepairWork(){
        new Thread(new FusibleRepairWork()).start();
    }

    //另起线程调用接口
    public static class Work<String> implements Callable<String>{
        private Integer parm;
        public Work(Integer parm){
            this.parm = parm;
        }
        @Override
        public String call() throws Exception {
            return (String) InterfaceProvidederTest.getMsgRandomSleep(parm);
        }
    }

    /**
     * 熔断修复  线程
     */
    public static class FusibleRepairWork implements Runnable{

        public synchronized static void doRun(){
            while (true){
                if(hystrixStatus == 2){
                    //做个休眠
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if((System.currentTimeMillis() - HystrixFuseTime) > HystrixFuseSpacingTime){
                        hystrixStatus = 3;//开关设置为半开
                        System.out.println("---------------" + "开启熔断修复成功");
                        break;
                    }
                }

            }
        }
        @Override
        public void run() {
            if(hystrixStatus == 2){
                System.out.println("---------------" + "开启熔断修复");
                doRun();
            }
        }
    }

    //判断是否需要熔断的线程
    public static class CheckIsFusibleWork implements Runnable{

        @Override
        public void run() {
            System.out.println("---------------" + "开启判断是否要熔断");
            while (true){
                int count = 0;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //只有在开关关闭时才执行判断
                if(hystrixStatus == 1){
                    Set<Long> set = Hystrixdata.keySet();
                    Iterator<Long> it =  set.iterator();
                    //这里就是参数设置  什么条件去执行熔断
                    while(it.hasNext()){
                        long oldTime = it.next();
                        if((oldTime + 1 * 60 * 1000) > System.currentTimeMillis() ){
                            count ++;
                        }else {
                            Hystrixdata.remove(oldTime);
                        }
                    }
                    if(count > 5){
                        System.out.println("---------------" + "设置熔断开关 为打开");
                        //设置熔断状态 和 熔断时间
                        hystrixStatus = 2;
                        HystrixFuseTime = System.currentTimeMillis();
                        //调起熔断回复
                        startFusibleRepairWork();
                    }
                }
            }
        }
    }
}
