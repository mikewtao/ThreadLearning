package com.mike.ThreadLearning.Traditional.ConsumerAndProducer;

public class Consumer implements Runnable {
	private Sstack ss = null;

	public Consumer(Sstack ss) {
		super();
		this.ss = ss;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("消费了" + ss.pop());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
