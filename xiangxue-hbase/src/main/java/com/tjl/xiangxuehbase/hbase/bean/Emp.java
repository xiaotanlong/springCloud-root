package com.tjl.xiangxuehbase.hbase.bean;

import java.io.Serializable;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/2/22 13:41
 */
public class Emp implements Serializable {
    private String ename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String job;
    private int age;
    private Dept dept;
    public void setDept(Dept dept) {
        this.dept = dept;
    }
    public Dept getDept() {
        return dept;
    }
    public void setAge(int age) {
        this.age=age;

    }
    public int getAge() {
        return age;
    }

    public  void setEname(String ename) {
        this.ename=ename;
    }
    public void setJob(String job) {
        this.job=job;
    }
    public String getJob() {
        return job;
    }
    public String getEname() {
        return ename;
    }
}
