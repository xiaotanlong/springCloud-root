package learn.aliyun;

import java.util.Arrays;

/**
 * QpsController
 *
 */
public class QpsController {

    protected int qps = 10;

    private Long[] accessWindow;

    private int curPosition;

    private long period;

    private final Object lock = new Object();

    public QpsController(int qpsLimit){
        this.qps = qpsLimit;
        this.period = 1000;
        this.accessWindow = new Long[this.qps];
        Arrays.fill(this.accessWindow, 0L);
        this.curPosition = 0;
    }

    //实现control的具体逻辑   可以任意增加个人认为的必要函数
    public  void control() {
        while (true)
        {
            if (this.isPass())
            {
                break;
            }
        }
    }

    public boolean isPass() {
        long curTime = System.currentTimeMillis();
        synchronized (lock) {
            if (curTime >= period + accessWindow[curPosition]) {
                accessWindow[curPosition++] = curTime;
                curPosition = curPosition % qps;
                return true;
            } else {
                return false;
            }
        }
    }

}
