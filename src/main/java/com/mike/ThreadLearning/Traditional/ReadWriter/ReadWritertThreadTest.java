package com.mike.ThreadLearning.Traditional.ReadWriter;

public class ReadWritertThreadTest {

	public static void main(String[] args) {
		Data data=new LockedData(10);
		new Thread(new ReaderThread(data)).start();
		new Thread(new ReaderThread(data)).start();
		new Thread(new ReaderThread(data)).start();
		new Thread(new ReaderThread(data)).start();
		new Thread(new WriterThread(data,"ABCDEFG")).start();
		new Thread(new WriterThread(data,"0123456789")).start();

	}

}


