package com.tjl.xiangxuehbase.service.base;

import java.util.List;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 基础接口  定义公共的hbase api方法
 * @date 2019/2/22 14:40
 */
public interface HbaseBaseService <T>  {
    /**
     * scan 查询
     * @param clazz
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<T> scan(Class<?> clazz, String tableName) throws Exception;

    /**
     * get 查询
     * @param clazz
     * @param tableName
     * @return
     * @throws Exception
     */
    public T get(Class<?> clazz, String tableName ,String rowKey)throws Exception;

    /**
     * scan 查询
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<T> scan( String tableName) throws Exception;
    /**
     * get 查询
     * @param tableName
     * @return
     * @throws Exception
     */
    public T get(String tableName ,String rowKey)throws Exception;
}
