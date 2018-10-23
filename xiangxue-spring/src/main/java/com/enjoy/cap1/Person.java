package com.enjoy.cap1;

public class Person {
	private String name;
	private Integer age;
	//如果一个bean类没有默认的构造方法，spring在创建bean实例时将会抛出如下运行时异常：
	public Person(){
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
