package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * @author scott
 */
public class CallableTest2 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		ExecutorService excutor=Executors.newSingleThreadExecutor();
		Future future=excutor.submit(new Callable(){
			public String call() throws Exception {
				Thread.sleep(5000L);
				return "hello thread!";
			}
		});
		try{
		 System.out.println("waiting for finish");
		 System.out.println(future.get());
		 excutor.shutdown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
