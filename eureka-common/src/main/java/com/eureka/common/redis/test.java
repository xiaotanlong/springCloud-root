package com.eureka.common.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class test {
    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    public test()
    {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();


    }

    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //config.setMaxActive(20);
        config.setMaxIdle(5);
        //config.setMaxWait(1000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();

        //config.setMaxActive(20);
        config.setMaxIdle(5);
        //config.setMaxWait(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    private void KeyOperate()
    {
        System.out.println("======================key==========================");
        // 清空数据
        System.out.println("清空库中所有数据："+jedis.flushDB());
        // 判断key否存在
        System.out.println("判断key999键是否存在："+shardedJedis.exists("key999"));
        System.out.println("新增key001,value001键值对："+shardedJedis.set("key001", "value001"));
        System.out.println("判断key001是否存在："+shardedJedis.exists("key001"));
        // 输出系统中所有的key
        System.out.println("新增key002,value002键值对："+shardedJedis.set("key002", "value002"));
        System.out.println("系统中所有键如下：");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
        // 删除某个key,若key不存在，则忽略该命令。
        System.out.println("系统中删除key002: "+jedis.del("key002"));
        System.out.println("判断key002是否存在："+shardedJedis.exists("key002"));
        // 设置 key001的过期时间
        System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
        }
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        // 移除某个key的生存时间
        System.out.println("移除key001的生存时间："+jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
        /*
         * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
         *             2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
         */
    }
    public void show() {
        KeyOperate();
    }
    public static void main(String[] args) {
        //new test().show();
        List<String> list = new ArrayList<String>(){{add("222");add("3333");}};
        list.add("444");
        List<String> list2 = new ArrayList<String>(){/* (non-Javadoc)
		 * @see java.util.ArrayList#add(int, java.lang.Object)
		 */

            @Override
            public void add(int index, String element) {
                // TODO Auto-generated method stub
                System.out.println(element + "------");
                tt(element);
                //super.add(index, element);
            }

            public void tt(String element){
                System.out.println(element + "------++");
            }
        };
        int i = 0;
        for (String ss : list){
            list2.add(i++,ss);
            //System.out.println(ss);
        }
        for (String ss : list2){
            //list2.tt(ss);
            System.out.println(ss + "----------------");
        }
    }
}
