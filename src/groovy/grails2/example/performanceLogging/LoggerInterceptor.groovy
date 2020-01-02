package grails2.example.performanceLogging

import groovy.util.logging.Log4j
import org.aspectj.lang.ProceedingJoinPoint
import org.springframework.util.StopWatch

@Log4j
public class LoggerInterceptor {
	public Object logPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch sw = new StopWatch( getClass().getSimpleName());
		try {
			sw.start(proceedingJoinPoint.getSignature().getName());
			return proceedingJoinPoint.proceed();
		} finally {
			sw.stop();
			def analyticsLoggingObject = [ 
					application: grails.util.Metadata.current.getApplicationName(),
					class_name: proceedingJoinPoint.getTarget().getClass().getName(),
					method: proceedingJoinPoint.getSignature().getName(),
					duration_ms: sw.totalTimeMillis
			]
			log.info( analyticsLoggingObject )
		}
	}
}
