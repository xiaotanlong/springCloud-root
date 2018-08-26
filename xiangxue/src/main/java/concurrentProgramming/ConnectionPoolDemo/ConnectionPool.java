package concurrentProgramming.ConnectionPoolDemo;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 数据库连接池类(用一句话描述该文件做什么)
 *
 * 代码性能优化中有一条----对于开销比较大的连接可以考虑使用连接池  http连接，数据库连接
 * 基本思路是这样  但是不一定是使用synchronized 关键字。并切 会提供容错，重连等机制
 * @date 2018.8.20
 */
public class ConnectionPool {
    static LinkedList<Connection> pool = new LinkedList<>();
    //初始化一定数量的连接池
    public ConnectionPool(int initPoolSize){
        for (int i=0;i< initPoolSize;i++){
            pool.addLast(new SqlConnectionImpl());
        }
    }
    //取连接池
    public Connection getCoon(Long overtime) throws InterruptedException {
        synchronized (pool){
            if(overtime < 0){
                while(pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                Long overtiomTime = System.currentTimeMillis() + overtime;
                Long weitTime = overtime;
                while(pool.isEmpty() && weitTime > 0){
                    pool.wait(weitTime);
                    weitTime = overtiomTime - System.currentTimeMillis();
                }
                Connection resurt = null;
                if(!pool.isEmpty()){
                    resurt = pool.removeFirst();
                }
                return resurt;

            }
        }

    }

    //放回数据库连接
    public void releaseConn(Connection conn) {
        if(conn!=null) {
            synchronized (pool) {
                pool.addLast(conn);
                pool.notifyAll();
            }
        }
    }
}
