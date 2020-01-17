package com.rquest.test.csvutil;

public class Student {

	private String username;

	private String password;

	private String name;

	private int age;

	public Student(String username, String password, String name, int age) {

		this.username = username;

		this.password = password;

		this.name = name;

		this.age = age;

	}

	public String getUsername() {

		return username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	public String getPassword() {

		return password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public int getAge() {

		return age;

	}

	public void setAge(int age) {

		this.age = age;

	}

	@Override

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("username : ").append(this.getUsername());

		sb.append(", password : ").append(this.getPassword());

		sb.append(", name : ").append(this.getName());

		sb.append(", age : ").append(this.getAge());

		return sb.toString();

	}

}