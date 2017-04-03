package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest1 {

	public static void main(String[] args) {
		ExecutorService excutor=Executors.newFixedThreadPool(3);
		for(int i=0;i<10;i++){
			final int task=i;
			excutor.submit(new Runnable() {		
				@Override
				public void run() {
					for(int j=0;j<10;j++){
					   System.out.println("Task:"+task+"; loop "+j);
						
					}
					
				}
			});
		}
		
		excutor.shutdown();

	}

}
