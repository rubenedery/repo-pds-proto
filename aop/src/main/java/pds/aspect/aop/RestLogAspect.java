package pds.aspect.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * @author RubenEdery on 22/10/2017.
 */

@Component
@Aspect
@Log
public class RestLogAspect {
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void restAnnotation() {}
	
	@Around("restAnnotation() && execution(* *(..))")
	public void restLog(ProceedingJoinPoint joinPoint) {
		final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		final Method method = signature.getMethod();
		
		final Class<?> controllerClass = method.getDeclaringClass();
		final RequestMapping controllerAnnotation = controllerClass.getAnnotation(RequestMapping.class);
		final String controllerRestPath = (controllerAnnotation.path().length == 0) ? "" : controllerAnnotation.path()[0];
		
		final RequestMapping controllerMethodAnnotation = method.getAnnotation(RequestMapping.class);
		final String methodRestPath = (controllerMethodAnnotation.path().length == 0) ? "" : controllerMethodAnnotation.path()[0];
		final RequestMethod requestMethod = (controllerMethodAnnotation.method().length == 0) ? RequestMethod.GET : controllerMethodAnnotation.method()[0];
		
		
		// @Before code
		try {
			joinPoint.proceed();
		} catch (Throwable throwable) {
			log.warning(String.format("[RestLogAspect] ", throwable.getMessage()));
		}
		// @After code
		
		log.info(String.format("[REST]"+
				controllerClass.getSimpleName()+
				requestMethod+
				controllerRestPath + methodRestPath+
				method.getName()
				)
		);
		
	}
	
}
