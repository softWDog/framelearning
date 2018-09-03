package org.smart4j.chapter4.common;

import org.springframework.stereotype.Component;

/**
 * @author: gethin
 * @create: 2018-08-31 10:28
 * @description:
 **/
@Component
public class HelloImpl implements Hello {
	
	public void say(String name) {
		System.out.println("Hello! " + name);
	}
	public void goodMorning(String name){
		System.out.println("Good Morning! "+name);
	}
	public void goodNight(String name){
		System.out.println("Good Night!"+name);
	}
}
