package org.smart4j.chapter4.springaop;

import org.aopalliance.intercept.MethodInvocation;
import org.smart4j.chapter4.common.Apology;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author: gethin
 * @create: 2018-08-31 16:38
 * @description:
 **/
@Component
public class HelloIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		return super.invoke(mi);
	}
	
	public void saySorry(String name) {
		System.out.println("Sorry! " + name);
	}
}
