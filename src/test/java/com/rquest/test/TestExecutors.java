package com.rquest.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestExecutors {
	
	private static List<Future<String>> lists = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
			
		Future<String> submit = executor.submit(new Task2());
		Future<String> submit2 = executor.submit(new Task1());
		
		lists.add(submit2);
		lists.add(submit);
		for (Future<String> string : lists) {
			System.out.println(string.get());
		}
		executor.shutdown();
		
	}
	
	

	
	

}


 class Task1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "task1";
	}
}


 class Task2 implements Callable<String>{
	
	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		return "task2";
	}
}



