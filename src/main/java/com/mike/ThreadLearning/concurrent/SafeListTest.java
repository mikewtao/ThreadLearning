package com.mike.ThreadLearning.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SafeListTest {
    
	public static void main(String[] args) {
		Collection<User> list=new ArrayList<User>();
		list.add(new User("张三",23));
		list.add(new User("李四",24));
		list.add(new User("王五",25));
		Iterator<User> it=list.iterator();
		while(it.hasNext()){
			User user=it.next();
			if("张三".equals(user.getName())){
				list.remove(user);
			}else{
				System.out.println(user);
			}
		}
		
	}

}

