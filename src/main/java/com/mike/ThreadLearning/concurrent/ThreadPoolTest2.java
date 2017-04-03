package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

	public static void main(String[] args) {
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("bombing.....");
				
			}
		}, 6,TimeUnit.SECONDS);
	}

}
