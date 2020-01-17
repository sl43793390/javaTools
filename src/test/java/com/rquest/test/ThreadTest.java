package com.rquest.test;

public class ThreadTest {

	public static void main(String[] args) {

		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				new ThreadTest().getString();
			}
		});
		
		t2.setName("线程二");
		t2.start();
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				new ThreadTest().getString();
			}
		});
		
		t3.setName("线程三");
		t3.start();
		
		for (int i = 0; i < 5; i++) {
			
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					new ThreadTest().getString();
				}
			});
			
			t1.setName("线程："+i);
			t1.start();
			
		}
		
		
	}
	
	private String getString() {
		int i = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i++;
		String result = Thread.currentThread().getName()+i;
		System.out.println(result);
		return result;
	}

}
