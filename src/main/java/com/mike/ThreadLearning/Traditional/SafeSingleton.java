package com.mike.ThreadLearning.Traditional;
/**
 * @author scott
 * 
 * 线程安全的单例模式
 * 
 */
public class SafeSingleton {
  
	private SafeSingleton(){};
	
	private static SafeSingleton singleton=null;
	
	public static SafeSingleton getInstance(){
		if(singleton==null){
			synchronized (singleton) {
				if(singleton==null){
					return new SafeSingleton();
				}
			}
		}
		return singleton;
	}
}
