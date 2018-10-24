package concurrentProgramming.deadlock.tranfer.service;

import concurrentProgramming.deadlock.tranfer.UserAccount;

import java.util.Random;

/**
 *类说明：不会产生死锁的安全转账第二种方法，尝试拿锁
 *
 * 动态的死锁：由于参数传递的不确定性，导致锁的顺序不一致
 * 动态顺序死锁，在实现时按照某种顺序加锁了，但是因为外部调用的问题，导致无法保证加锁顺序而产生的。
	解决：
	1、	通过内在排序，保证加锁的顺序性
	2、	通过尝试拿锁，也可以。
 * 活锁：活锁指的是任务或者执行者没有被阻塞，由于某些条件没有满足，导致一直重复尝试—失败—尝试—失败的过程。
 * 		 处于活锁的实体是在不断的改变状态，活锁有可能自行解开。
 * 注：在死循环获取资源代码中加入休眠（随机数）作用：避免活锁 ---作用  错开了多个线程那锁的时间
 *
 * 解决根本思路：把加锁的顺序由不确定改成确定
 */
public class SafeOperateToo implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {
    	Random r = new Random();
    	while(true) {
    		if(from.getLock().tryLock()) {
    			try {
    				System.out.println(Thread.currentThread().getName()
    						+" get "+from.getName());
    				if(to.getLock().tryLock()) {
    					try {
    	    				System.out.println(Thread.currentThread().getName()
    	    						+" get "+to.getName());    						
    						//两把锁都拿到了
    	                    from.flyMoney(amount);
    	                    to.addMoney(amount);
    	                    break;
    					}finally {
    						to.getLock().unlock();
    					}
    				}
    			}finally {
    				from.getLock().unlock();
    			}
    		}
    		//休眠随机数  --错开各个线程那锁的时间，避免活锁
			Thread.sleep(r.nextInt(10));
    	}
    }
}
