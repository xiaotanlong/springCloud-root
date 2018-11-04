package cn.enjoy.zk.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zk原生的api
 */
public class CreateSessionDemo  {
    private final static String CONNECTSTRING="47.107.79.2:2181";
    private static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException {
        //zk的监听  Watcher  监听拦截状态  通知客户端  状态变更。保证使用连接做增删改查的时候  连接是有效的
        ZooKeeper zooKeeper=new ZooKeeper(CONNECTSTRING, 5000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //如果当前的连接状态是连接成功的，那么通过计数器去控制
                if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                    System.out.println(watchedEvent.getState());
                }
            }
        });
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
    }
}
