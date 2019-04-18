package com.xiangxue.ch5.bq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 *一个Delayed 对象
 * 还可以实现 Runnable  异步调度业务
 *类说明：存放到队列的元素
 */
public class ItemVo<T> implements Delayed{
	
	private long activeTime;//到期时间，单位毫秒
	private T date;
	
	//activeTime是个过期时长
	public ItemVo(long activeTime, T date) {
		super();
		//将传入的时长转换为超时的时刻
		//有效时间+系统时间（转纳秒）
		this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, 
				TimeUnit.MILLISECONDS) + System.nanoTime();
		this.date = date;
	}
	
	public long getActiveTime() {
		return activeTime;
	}

	public T getDate() {
		return date;
	}

	//按照剩余时间排序
	@Override
	public int compareTo(Delayed o) {
		long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		return (d == 0) ? 0 :((d > 0) ? 1 : -1);
	}
	/**
	 *  返回与此对象相关的剩余延迟时间，以给定的时间单位表示。
	 *  用到期时间 - 当前系统时间
	 *  System.nanoTime---返回的是纳秒
	 * @param unit
	 * @return
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		long d = unit.convert(this.activeTime - System.nanoTime(),
				TimeUnit.NANOSECONDS);
		return d;
	}
	



}
