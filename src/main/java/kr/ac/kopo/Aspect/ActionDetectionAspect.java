package kr.ac.kopo.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ActionDetectionAspect {

	@Around("execution(* kr.ac.kopo.controller.*.*(..))")
	public Object controllerDetection(ProceedingJoinPoint joinPoint) {
		// 디버그 편의성 개선용
		
		String className = joinPoint.getTarget().getClass().getName().replace("kr.ac.kopo.", "");
		String mathodName = joinPoint.getSignature().getName();
		
		System.out.println("< " + className + "." + mathodName +" >\n");
		
		Object result = null;
		try {
			result = joinPoint.proceed();
			System.out.println("\n</ " + className + "." + mathodName + " >");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@Around("execution(* kr.ac.kopo.service.*.*(..))")
	public Object serviceDetection(ProceedingJoinPoint joinPoint) {
		// 디버그 편의성 개선용
		
		String className = joinPoint.getTarget().getClass().getName().replace("kr.ac.kopo.", "");
		String mathodName = joinPoint.getSignature().getName();
		
		System.out.println("    < " + className + "." + mathodName +" >\n");
		
		Object result = null;
		try {
			result = joinPoint.proceed();
			System.out.println("\n    </ " + className + "." + mathodName + " >");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
