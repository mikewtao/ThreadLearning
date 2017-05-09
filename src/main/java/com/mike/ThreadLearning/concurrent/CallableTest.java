package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * @author scott
 * @date 2017年4月3日
 * @desc 批量等待线程执行结果返回
 */
public class CallableTest {

	public static void main(String[] args) {
		ExecutorService service=Executors.newFixedThreadPool(3);
		CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(service);
		for(int i=0;i<10;i++){
			final int task=i;
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					Thread.sleep(5000);
					return task;
				}
				
			});
		}
		
		for(int j=0;j<10;j++){
			try {
				Integer i=completionService.take().get();
				System.out.println(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		service.shutdown();
	}

}
