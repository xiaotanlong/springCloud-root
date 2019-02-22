package com.tjl.xiangxuehbase.hbase;

import com.tjl.xiangxuehbase.hbase.bean.Emp;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: hbase 反射处理
 * @date 2019/2/22 10:54
 */
public class HbaseBeanUtil {

    public static void main(String[] args) {
        String value="ename:Simth|job:Clerk|age:11|dept.dname:财务部|dept.company.name:csDN";//输入的时候通过|来区分属性，用：来连接属性的值
        Emp emp=HbaseBeanUtil.creat(Emp.class, value);
        System.out.println("姓名："+emp.getEname()+"工作："+emp.getJob()+"年龄"+emp.getAge());
        System.out.println(emp.getDept().getDname());
        System.out.println(emp.getDept().getCompany().getName());
    }

    /**
     * 返回一个已经设置好属性内容的实例化对象
     * @param clazz
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T creat(Class<?> clazz,String value) {
        try {
            //要想改变属性的内容必须实例化对象
            Object obj = clazz.getDeclaredConstructor().newInstance();
            //对对象的属性进行赋值（内容）
            HbaseBeanUtil.setValue(obj, value);
            return (T) obj;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


    /**
     * value demo ename:Simth|job:Clerk|age:11|dept.dname:财务部|dept.company.name:csDN
     * @param obj 需要更改属性的对象
     * @param value
     */
    public static void setValue(Object obj,String value) {
        String results[] = value.split("\\|");
        for (int x = 0; x < results.length; x++) {
            //attval[0]是属性的名称，而attval[1]是属性的内容
            String attval[] = results[x].split(":");
            try {
                //包含点号说明了有多级的关系
                if (attval[0].contains(".")) {
                    String temp[] = attval[0].split("\\.");
                    //当前的对象
                    Object currentObject = obj;
                    //长度减一，因为最后一位是属性
                    for (int y = 0; y < temp.length - 1; y++) {
                        Method method = currentObject.getClass().getDeclaredMethod("get" + initcap(temp[y]));
                        Object tempObject = method.invoke(currentObject);
                        if (tempObject == null) {
                            Field field = currentObject.getClass().getDeclaredField(temp[y]);
                            Method setMethod = currentObject.getClass().getDeclaredMethod("set" + initcap(temp[y]), field.getType());
                            Object newObject = field.getType().getDeclaredConstructor().newInstance();
                            setMethod.invoke(currentObject, newObject);
                            currentObject = newObject;
                        } else {
                            currentObject = tempObject;
                        }
                    }
                    //进行属性内容的设置
                    Field field = currentObject.getClass().getDeclaredField(temp[temp.length - 1]);
                    Method method = currentObject.getClass().getDeclaredMethod("set" + initcap(temp[temp.length - 1]), field.getType());
                    Object convertValue = convertAttributeValue(field.getType().getName(), attval[1]);
                    method.invoke(currentObject, convertValue);

                } else {
                    Field field = obj.getClass().getDeclaredField(attval[0]);
                    Method setMethod = obj.getClass().getDeclaredMethod("set" + initcap(attval[0]), field.getType());
                    Object convertAttributeValue = convertAttributeValue(field.getType().getName(), attval[1]);
                    setMethod.invoke(obj, convertAttributeValue);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }

    /**
     * 判空
     * @param str
     * @return
     */
    public static String initcap(String str) {
        if(str == null||"".equals(str)) {
            return str;
        }
        if(str.length()==1) {
            return str.toUpperCase();
        }else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 类型转换
     * @param type
     * @param value
     * @return
     */
    private static Object convertAttributeValue(String type,String value) {
        if("long".equals(type)||"java.lang.Long".equals(type)){
            return Long.parseLong(value);
        }else if("int".equals(type)||"java.lang.int".equals(type)) {
            return Integer.parseInt(value);
        }else if("double".equals(type)||"java.lang.double".equals(type)) {
            return Double.parseDouble(value);
        }else if("java.util.Date".equals(value)) {
            SimpleDateFormat sdf=null;
            if(value.matches("\\d{4}-\\d{2}-\\d{2}")) {
                sdf=new SimpleDateFormat("yyyy-MM-dd");
            }else if(value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }else {
                return new Date();
            }
            try {
                return sdf.parse(value);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                return new Date();
            }
        }
        return value;

    }


}
