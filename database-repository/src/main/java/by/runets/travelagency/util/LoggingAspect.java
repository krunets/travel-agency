package by.runets.travelagency.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
	/*
		This is method which scan all annotated classes or methods and add to pointcut.
	 */
	@Pointcut("@annotation(by.runets.travelagency.util.annotation.Loggable)")
	public void allAnnotatedMethods () {
	}
	
	@Before("allAnnotatedMethods()")
	public void beforeMethodInvoke (JoinPoint joinPoint) {
		log.info("The method " + joinPoint.getSignature().getName() + " has been invoked.");
	}
	
	@After("allAnnotatedMethods()")
	public void afterMethodInvoke (JoinPoint joinPoint) {
		log.info("The method " + joinPoint.getSignature().getName() + " has been ended.");
	}
}