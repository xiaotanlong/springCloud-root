package com.tjl.xiangxuehbase.service.base;

import com.tjl.xiangxuehbase.hbase.HbaseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 基础实现类 ---实现公共的hbase api方法
 * @date 2019/2/22 14:40
 */
public class IHbaseBaseService <T extends Serializable> implements HbaseBaseService<T>{
    @Autowired(required = false)
    public HbaseTemplate hbaseTemplate;

    public List<T> scan(Class<?> clazz, String tableName) throws Exception {
        return hbaseTemplate.scan(clazz, tableName);
    }

    public List<T> scan(String tableName) throws Exception {
        Class<T> clz = getEntityClassType();
        return hbaseTemplate.scan(clz, tableName);
    }

    public T get(Class<?> clazz, String tableName, String rowKey) throws Exception {
        return (T) hbaseTemplate.get(clazz, tableName, rowKey);
    }

    public T get(String tableName, String rowKey) throws Exception {
        Class<T> clz = getEntityClassType();
        return (T) hbaseTemplate.get(clz, tableName, rowKey);
    }


    /**
     * 获取泛型Class信息
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClassType() {
        Class<?> clz = getClassType(0);
        Assert.notNull(clz, "BaseService没有指定泛型!!");
        return (Class<T>) clz;
    }
    /**
     * 获取泛型Class信息
     *
     * @return
     */
    private Class<?> getClassType(int pos) {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] genericTypes = parameterizedType.getActualTypeArguments();
            if (genericTypes.length >= pos + 1) {
                return (Class<?>) genericTypes[pos];
            }
        }
        return null;
    }
}
