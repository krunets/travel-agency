package by.runets.travelagency.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
	
	@Around(value = "allAnnotatedMethods()")
	public Object around(final ProceedingJoinPoint joinPoint) {
		final Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		
		String methodName = method.getName();
		String className = method.getDeclaringClass().getName();
		
		log.info("Entering to " + methodName + " from class " + className);
	
		Object object = null;
		try {
			object = joinPoint.proceed();
		} catch (Throwable throwable) {
			logException(joinPoint, throwable);
		}
		log.info("Exciting from " + methodName + " from class " + className);
		
		return object;
	}
	
	private void logException(final JoinPoint joinPoint, final Throwable exception) {
		final Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		
		String methodName = method.getName();
		String className = method.getDeclaringClass().getName();
		Object args = joinPoint.getArgs();
		
		log.error("Method " + methodName + " from class " + className + " method args " + args + " threw " + exception);
	}
}