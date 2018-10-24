package concurrentProgramming.deadlock.tranfer.service;

import concurrentProgramming.deadlock.tranfer.UserAccount;

/**
 *类说明：不会产生死锁的安全转账
 *
 * 动态的死锁：由于参数传递的不确定性，导致锁的顺序不一致
 * 动态顺序死锁，在实现时按照某种顺序加锁了，但是因为外部调用的问题，导致无法保证加锁顺序而产生的。
        解决：
        1、	通过内在排序，保证加锁的顺序性
        2、	通过尝试拿锁，也可以。

 */
public class SafeOperate implements ITransfer {
	private static Object tieLock = new Object();//加时赛锁

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {

        //返回给定对象的哈希码，该代码与默认的方法 hashCode() 返回的代码一样，无论给定对象的类是否重写 hashCode()。
    	int fromHash = System.identityHashCode(from);
    	int toHash = System.identityHashCode(to);
    	//如果可以确保一个属性不会重复，可以用该属性进行比较  大小 保证顺序获取锁的一致性
    	//先锁hash小的那个
    	if(fromHash<toHash) {
            synchronized (from){
                System.out.println(Thread.currentThread().getName()
                		+" get"+from.getName());
                Thread.sleep(100);
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()
                    		+" get"+to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                }
            }    		
    	}else if(toHash<fromHash) {
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                		+" get"+to.getName());
                Thread.sleep(100);
                synchronized (from){
                    System.out.println(Thread.currentThread().getName()
                    		+" get"+from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                }
            }    		
    	}else {//解决hash冲突的方法
    		synchronized (tieLock) {
				synchronized (from) {
					synchronized (to) {
	                    from.flyMoney(amount);
	                    to.addMoney(amount);						
					}
				}
			}
    	}
    	
    }
}
