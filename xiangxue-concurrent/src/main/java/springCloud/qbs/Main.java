package springCloud.qbs;

/**
 * Main
 * QPS：queries per second ：每秒的请求数
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //线程数和qps值都不要设置过大，避免系统性能差别导致测试断言不准  建议线程数控制在2位数  qps控制在3位数
        int threadSize = 10;
        int qpsLimit = 100;
        final QpsController controller = new QpsController(qpsLimit);

        for(int i = 0 ; i < threadSize ; i++){
            Thread t  = new Thread(){
                Worker worker = new Worker();
                @Override
                public void run() {
                    while(true){
                        controller.control();
                        worker.work();
                    }
                }
            };
            t.setName("worker-threads- " + i);
            t.start();
        }

        int loopCount = 1;
        while(true){
            //在循环稳定运行几轮之后  观察worker.getcount和except的值是否相等  相等表示限制已经开始生效
            //第一轮循环中  work线程和主线程之间由于竞争关系  可能会出现count值和except值不相等的情况
            System.out.println(Worker.getCount() + " -- except " + (qpsLimit * loopCount));
            Thread.sleep(1000);
            loopCount++;
        }

    }

}

