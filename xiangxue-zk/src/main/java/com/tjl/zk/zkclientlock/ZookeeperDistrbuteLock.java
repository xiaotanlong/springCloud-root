package com.tjl.zk.zkclientlock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 性能低  所有节点监听最前面的节点
 * @date 2019/1/24 10:09
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock{
    private CountDownLatch countDownLatch = null;
    @Override
    //尝试获得锁
    public  boolean tryLock() {
        try {
            //创建零时唯一节点
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            //如果创建失败报出异常
//			e.printStackTrace();
            return false;
        }

    }
    //创建失败后 等待  监听
    @Override
    public void waitLock() {
        IZkDataListener izkDataListener = new IZkDataListener() {
            public void handleDataDeleted(String path) throws Exception {
                // 唤醒被等待的线程
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
            public void handleDataChange(String path, Object data) throws Exception {

            }
        };
        // 注册事件
        zkClient.subscribeDataChanges(PATH, izkDataListener);

        //如果节点存
        if (zkClient.exists(PATH)) {
            countDownLatch = new CountDownLatch(1);
            try {
                //等待，一直等到接受到事件通知  等待  handleDataDeleted  节点移除通知
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 删除监听
        zkClient.unsubscribeDataChanges(PATH, izkDataListener);
        //waitLock  结束  继续tryLock
    }

    public void unLock() {
        //释放锁
        if (zkClient != null) {
            zkClient.delete(PATH);
            zkClient.close();
            System.out.println("释放锁资源...");
        }
    }
}
