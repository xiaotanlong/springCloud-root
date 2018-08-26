package concurrentProgramming.threaddemo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 虚拟机线程管理接口
 * @date 8.16
 *
 *
 * [6] : Monitor Ctrl-Break
 * [5] : Attach Listener // 获取当前程序运行的相关信息  包括内存
 * [4] : Signal Dispatcher  // 分发处理给JVM信号的线程
 * [3] : Finalizer //调用对象finalize方法的线程  结束的时候去执行，清理资源  不保证一定会执行
 * [2] : Reference Handler //清除reference线程
 * 1] : main //main线程,程序入口
 */
public class ThreadMXBeanMain {
    public static void main(String[] arrs){
        //虚拟机线程管理接口
        ThreadMXBean bead = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threads = bead.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo : threads){
            System.out.println("[" + threadInfo.getThreadId() + "] : " + threadInfo.getThreadName());
        }
    }
}
