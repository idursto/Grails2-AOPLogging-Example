import grails2.example.performanceLogging.CustomJDBCAppender

// Place your Spring DSL code here
beans = {

	xmlns aop:"http://www.springframework.org/schema/aop"
	loggerInterceptorAspect( grails2.example.performanceLogging.LoggerInterceptor )
	aop {
		config("proxy-target-class": true) {
			aspect(id: "performanceLoggerAspect", ref: "loggerInterceptorAspect") {
				around method: "logPerformance",
				       pointcut: "within(grails2.example..*) && !execution(* *.getMetaClass(..)))"

				// For reference from a pointcut perspective:
				//  "execution(* grails2.example.*.*(..)) || execution(* grails2.example.services.*.*(..))" <-- Matches specifically on example & example.services class methods
				//  "within(grails2.example..*)" <-- Matches all methods defined in classes inside the package and its sub-packages
			}
		}
	}
	
	customJDBCAppender( CustomJDBCAppender ) {}
}
