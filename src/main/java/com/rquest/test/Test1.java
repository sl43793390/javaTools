package com.rquest.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.ClassLoaderUtil;


public class Test1 {

//	通过反射调用其他类的方法
	public static void main(String[] args) {
//			String randomAlphabetic = RandomStringUtils.randomAlphanumeric(5);
			try {
				Class<?> loadClass = Class.forName("com.rquest.test.QrcodeTest");
				Class<?> loadClass2 = Class.forName("com.rquest.test.QrcodeTest", true, new ClassLoader() {});
				Method[] methods = loadClass.getMethods();
				for (int i = 0; i < methods.length; i++) {
					System.out.println(methods[i].getParameterTypes());
					if (methods[i].getName().equals("demo")) {
						try {
							methods[i].invoke(loadClass);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	


}
