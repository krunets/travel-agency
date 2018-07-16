package by.runets.travelagency.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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
	private static final String ENTERING_TO = "Entering to ";
	private static final String EXCITING_FROM = "Exciting from ";
	private static final String FROM_CLASS = " from class ";
	private static final String METHOD = "Method ";
	private static final String THREW = " threw ";
	
	@Pointcut("@annotation(by.runets.travelagency.util.annotation.Loggable)")
	public void allAnnotatedMethods () {
		//		This is method which scan all annotated classes or methods and add to pointcut.
	}
	
	@Around(value = "allAnnotatedMethods()")
	public Object around(final ProceedingJoinPoint joinPoint) {
		final Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		
		String methodName = method.getName();
		String className = method.getDeclaringClass().getName();
		
		log.info(ENTERING_TO + methodName + FROM_CLASS + className);
	
		Object object = null;
		try {
			object = joinPoint.proceed();
		} catch (Throwable throwable) {
			logException(joinPoint, throwable);
		}
		log.info(EXCITING_FROM + methodName + FROM_CLASS + className);
		
		return object;
	}
	
	private void logException(final JoinPoint joinPoint, final Throwable exception) {
		final Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		
		String methodName = method.getName();
		String className = method.getDeclaringClass().getName();
		
		log.error(METHOD + methodName + FROM_CLASS + className  + THREW + exception);
	}
}