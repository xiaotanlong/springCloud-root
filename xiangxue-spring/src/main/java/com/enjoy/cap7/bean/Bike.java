package com.enjoy.cap7.bean;

/**
 * 1,  指定初始化init-method方法
 2,  指定销毁destory-method方法
	注:对于单实例的bean, 可以正常调用初始化和销毁方法
 	对于多实例的bean,容器只负责初始化, 但不会管理bean, 容器关闭时不会调用销毁方法
 */
public class Bike {
	//构造方法是 类创建实例时调用的
	public Bike(){
		System.out.println("Bike constructor..............");
	}
	//
	public void init(){
		System.out.println("Bike .....init.....");
	}
	//
	public void destory(){
		System.out.println("Bike.....destory");
	}
}
