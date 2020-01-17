package com.rquest.test.entity;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2387868828978899885L;
	private String userId;
	private Integer age;
	private Double [] [] value1;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double[][] getValue1() {
		return value1;
	}
	public void setValue1(Double[][] value1) {
		this.value1 = value1;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userId, Integer age, Double[][] value1) {
		super();
		this.userId = userId;
		this.age = age;
		this.value1 = value1;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", age=" + age + ", value1=" + Arrays.toString(value1) + "]";
	}
	
	
}
