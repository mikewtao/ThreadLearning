package com.mike.ThreadLearning.Traditional.ReadWriter;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockedData implements Data{
	private final char[] buffer;
	private final ReadWriteLock lock = new ReentrantReadWriteLock(); // 创建读写锁
    public LockedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }
 
    @Override
	public /*synchronized*/ String read() {
        StringBuilder result = new StringBuilder();
        lock.readLock().lock();
        try{
            for (char c : buffer) {
                result.append(c);
            }
            sleep(100);
        }finally {
			lock.readLock().unlock();
		}
        return result.toString();
    }
 
    @Override
	public /*synchronized*/ void write(char c) {
    	lock.writeLock().lock();
    	try{
    		for (int i = 0; i < buffer.length; i++) {
                buffer[i] = c;
                sleep(100);
            }
    	}finally {
			lock.writeLock().unlock();
		}
        
    }
 
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}