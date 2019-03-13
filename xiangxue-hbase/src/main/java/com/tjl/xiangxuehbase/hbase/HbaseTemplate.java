package com.tjl.xiangxuehbase.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: java 操作hbase  工具类
 * @date 2019/2/21 10:23
 * 相关记录：
 *   1.获取Htable的方式
 *      table = connection.getTable(TableName.valueOf(tableName));
        table = new HTable(configuration,tableName);

     2.查询相关
        table.get()  根据rowKey 获取单条数据
        ResultScanner scanner = table.getScanner(scan);  查询列表
     3.hbase 只支持简单查询  如果需要排序，过滤，等查询  考虑使用 中间索引工具  类似 solr

     4.table.isAutoFlush() = true， 这种情况下，对表的单行操作会实时发送到服务端完成。

     5.HBase中提供了三种资源池的实现，分别是Reusable，RoundRobin和ThreadLocal。
            具体实现可以通过hbase.client.ipc.pool.type配置项指定，默认为Reusable。
            连接池的大小也可以通过hbase.client.ipc.pool.size配置项指定，默认为1。
 *
 */
public class HbaseTemplate<T,R> implements InitializingBean {
    //链接配置
    private Configuration configuration;
    //Hbase链接
    private Connection connection = null;

    public HbaseTemplate(){}

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 关闭链接  配置销毁方法
     * @throws Exception
     */
    private void closeConnection(){
        if(connection != null){
            try{
                connection.close();
                System.out.println("-------关闭connection成功！------");
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 建表demo
     */
    public void createTable() {
        // 创建表管理类
        HBaseAdmin admin = null;
        try{
            admin = new HBaseAdmin(connection); // hbase表管理
            // 创建表描述类
            TableName tableName = TableName.valueOf("dept"); // 表名称
            HTableDescriptor desc = new HTableDescriptor(tableName);
            // 创建列族的描述类
            HColumnDescriptor family = new HColumnDescriptor("info"); // 列族
            // 将列族添加到表中
            desc.addFamily(family);
            HColumnDescriptor family2 = new HColumnDescriptor("subdept"); // 列族
            // 将列族添加到表中
            desc.addFamily(family2);
            // 创建表
            admin.createTable(desc); // 创建表
            System.out.println("创建表成功！");
            admin.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(admin != null){
                    admin.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * hbase scan 查询
     * @param clazz
     * @param tableName
     * @return
     */
    public List<T> scan(Class<?> clazz,String tableName) {
        List<T> resurtList = new ArrayList<>();
        Table table = null;
        try{
            table = connection.getTable(TableName.valueOf(tableName));
            // 创建全表扫描的scan
            Scan scan = new Scan();
            // 打印结果集
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                if(result != null){
                    StringBuilder sb = new StringBuilder();
                    for (KeyValue kv1 : result.raw()) {
                        System.out.print(new String(kv1.getRow()) + " ");
                        System.out.print(new String(kv1.getFamily()) + ":");
                        System.out.print(new String(kv1.getQualifier()) + " = ");
                        System.out.print(new String(kv1.getValue()));
                        System.out.print(" timestamp = " + kv1.getTimestamp() + "\n");
                        sb.append(new String(kv1.getQualifier()) + ":" + new String(kv1.getValue()));
                        sb.append("\\|");
                    }
                    resurtList.add((T)HbaseBeanUtil.creat(clazz,sb.toString()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(table != null){
                    table.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return resurtList;
    }
    /**
     * 根据rowKey 查询 一条数据
     * @param tableName 表名
     * @param rowKey
     * @return
     */
    public <T> T get(Class<?> clazz,String tableName,String rowKey) {
        Table table = null;
        T r = null;
        try{
            table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);
            StringBuilder sb = new StringBuilder();
            if(result != null){
                for (KeyValue kv1 : result.raw()) {
                    System.out.print(new String(kv1.getRow()) + " ");
                    System.out.print(new String(kv1.getFamily()) + ":");
                    System.out.print(new String(kv1.getQualifier()) + " = ");
                    System.out.print(new String(kv1.getValue()));
                    System.out.print(" timestamp = " + kv1.getTimestamp() + "\n");
                    sb.append(new String(kv1.getQualifier()) + ":" + new String(kv1.getValue()));
                }
            }
            r = HbaseBeanUtil.creat(clazz,sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(table != null){
                    table.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return r;
    }





    /**
     * 创建链接
     */
    private void createConnection(){
        try {
            if(connection == null){
                connection = ConnectionFactory.createConnection(configuration);
            }
            System.out.println("---------创建connection成功！-----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化链接 或者在 @Bean 那里使用  init  方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        createConnection();
    }
}
