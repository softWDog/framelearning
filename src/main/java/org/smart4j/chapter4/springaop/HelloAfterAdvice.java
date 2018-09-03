package org.smart4j.chapter4.springaop;
		
		import org.springframework.aop.AfterAdvice;
		import org.springframework.aop.AfterReturningAdvice;
		
		import java.lang.reflect.Method;

/**
 * @author: gethin
 * @create: 2018-08-31 13:18
 * @description:
 **/
public class HelloAfterAdvice implements AfterReturningAdvice {
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("After");
	}
}
