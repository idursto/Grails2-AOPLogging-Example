package grails2.logging

class Logging {
	String application
	
	String className
	
	String method
	
	String note
	
	Date date
	
	Long durationMs

	static constraints = {
		application nullable: false
		className nullable: false
		method nullable: false
		note nullable: true
		date nullable: false
		durationMs nullable: true
	}
}
