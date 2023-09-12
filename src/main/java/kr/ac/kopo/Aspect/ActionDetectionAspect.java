package kr.ac.kopo.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ActionDetectionAspect {

	@Before("execution(* kr.ac.kopo.controller.*.*(..))")
	public void thePrintDetection(JoinPoint joinPoint) {
		System.out.println("< controller Action : " + joinPoint.getSignature().getName() + " >\n");
	}
}
