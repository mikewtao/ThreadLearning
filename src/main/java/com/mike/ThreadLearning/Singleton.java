package com.mike.ThreadLearning;
/**
 * @author scott
 * 
 * 线程安全的单例模式
 * 
 */
public class Singleton {
  
	private Singleton(){};
	
	private static Singleton singleton=null;
	
	public static Singleton getInstance(){
		if(singleton==null){
			synchronized (singleton) {
				if(singleton==null){
					return new Singleton();
				}
			}
		}
		return singleton;
	}
}
