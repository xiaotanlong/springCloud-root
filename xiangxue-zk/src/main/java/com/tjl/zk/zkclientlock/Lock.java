package com.tjl.zk.zkclientlock;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description:
 * @date 2019/1/24 10:07
 */
public interface Lock {
    //获取到锁的资源
    public void getLock();
    // 释放锁
    public void unLock();
}
