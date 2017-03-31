package com.mike.ThreadLearning.ConsumerAndProducer;

public class ConsumeAndProduceTest {
    public static void main(String[] args) {
		Sstack ss=new Sstack();
		Thread t1=new Thread(new Producer(ss));
		Thread t2=new Thread(new Consumer(ss));
		t1.start();
		t2.start();
	}
}
