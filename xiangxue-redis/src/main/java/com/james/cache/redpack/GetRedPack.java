package com.james.cache.redpack;

import com.james.cache.basic.Basic;
import com.james.cache.utils.JedisUtils;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * 多线程模拟用户抢红包
 */
public class GetRedPack {
	private static String HongBaoShaID = "";
    //抢红包的方法
	static public void getHongBao() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(Basic.threadCount);//20 //发枪器
		for(int i = 0 ; i < Basic.threadCount ; i ++){  //20线程
			Thread thread = new Thread(){
				public void run(){
					//拿到jedis连接
					JedisUtils jedis = new JedisUtils(Basic.ip, Basic.port, Basic.auth);
					while(true){
						//模拟一个用户ID使用UUID XXXX
						String userid = UUID.randomUUID().toString();
						//抢红包  eval 可以直接调用我们LUA脚本里的业务代码  object ={rid_1:1, money:9, userid:001}
						//Object object = jedis.eval(Basic.getHongBaoScript,4,Basic.hongBaoPoolKey,Basic.hongBaoDetailListKey,Basic.userIdRecordKey,userid);

						//改造后的 代码
						if(HongBaoShaID.equals("")){
							String dir = System.getProperty("user.dir");
							//HongBaoShaID = jedis.scriptLoad(Basic.getHongBaoScript);
							HongBaoShaID = jedis.scriptLoad(getBytes(this.getClass().getResource("/lua/hubao.lua").getPath()));
						}
						Object object = jedis.evalsha(HongBaoShaID,4,Basic.hongBaoPoolKey,Basic.hongBaoDetailListKey,Basic.userIdRecordKey,userid);

						
						if(null != object){
							System.out.println("用户ID号："+userid+" 抢到红包的详情是 "+object);
						}else{
							if(jedis.llen(Basic.hongBaoPoolKey) == 0){
								break;
							}
						}
					}
					latch.countDown();
				}
		};
		thread.start();

		}
		latch.await();
	}

	/**
	 * 获取文件 byte[]数组
	 * @param filePath
	 * @return
	 */
	static public byte[] getBytes(String filePath){
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
