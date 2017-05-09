package com.mike.ThreadLearning.Traditional.ConsumerAndProducer;

public class Producer implements Runnable {
	private Sstack ss=null;
	
	public Producer(Sstack ss) {
		this.ss = ss;
	}
	
    @Override
	public void run() {
	  for(int i=0;i<20;i++){
		  ss.push("a"+i);
		  System.out.println("生产了"+"a"+i);
		  try {
			Thread.sleep(10);
		 } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

	}

}
