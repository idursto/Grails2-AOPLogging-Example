import grails2.example.performanceLogging.CustomJDBCAppender
import org.apache.log4j.AsyncAppender

// log4j configuration
log4j.main = {
	appenders {
		console name: "stdout", layout: pattern( conversionPattern: '[%p] %d [%t] %x %c %M - %m%n' )
		
		org.apache.log4j.Appender customJdbcAppender = new CustomJDBCAppender(
				name: 'jdbcAppender',
				driver: 'com.microsoft.sqlserver.jdbc.SQLServerDriver',
				url: 'jdbc:sqlserver://VA1-DSPLAPP049;databaseName=aopLogging',
				user: 'grailsdbm',
				password: 'grailsdbm'
		)
		
		def asyncAppender = new AsyncAppender(
				name:  "asyncAppender",
				locationInfo: true
		)
		
		asyncAppender.addAppender( customJdbcAppender )
		
		appender asyncAppender
	}
	root {
		error "stdout"
	}
	info additivity: false, 'asyncAppender': [ "grails2.example.performanceLogging" ]
	info additivity: false, 'stdout': [ "grails2.example.performanceLogging" ]
}
