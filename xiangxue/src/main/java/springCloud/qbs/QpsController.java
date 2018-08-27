package springCloud.qbs;

import java.util.Arrays;

/**
 * QpsController
 */
public class QpsController {
    private long period;//时间间隔
    protected int qps = 10;//限制次数
    private Long[] accessWindow;//存放每次请求的时间戳记录
    private int curPosition;//数组下标记录

    private final Object lock = new Object();

    public QpsController(int qpsLimit) {
        this.qps = qpsLimit;
        this.period = 1000;
        this.accessWindow = new Long[this.qps];
        Arrays.fill(this.accessWindow, 0L);
        this.curPosition = 0;
    }

    //实现control的具体逻辑   可以任意增加个人认为的必要函数
    public void control() {
        while (true) {
            if (this.isPass()) {
                break;
            }
        }
    }

    /**
     * 一开始  curTime >= period + accessWindow[curPosition]  条件都满足 会返回true
     * 当数组填充满了后，会每次拿数组中最小的时间点进行比较，是否已经大于指定间隔时间，若大于则 返回ture 并重置最小时间
     * 若小于  则说明在指定间隔时间内，已经有超过指定次数的请求，则之间返回false,继续循环等待
     * @return
     */
    public boolean isPass() {
        long curTime = System.currentTimeMillis();
        synchronized (lock) {
            //当前时间 大于 最早成功的那一个  时间点  则 返回true,并将改时间点重置为最新（当前时间）
            if (curTime - period >= accessWindow[curPosition]) {
                accessWindow[curPosition++] = curTime;
                curPosition = curPosition % qps;
                return true;
            } else {
                return false;
            }
        }
    }

}
