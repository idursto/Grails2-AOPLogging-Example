import grails2.example.performanceLogging.CustomJDBCAppender

// log4j configuration
log4j.main = {
	appenders {
		console name: "stdout", layout: pattern( conversionPattern: '[%p] %d [%t] %x %c %M - %m%n' )

		appender new CustomJDBCAppender(
				name: 'jdbcAppender',
				driver: 'com.microsoft.sqlserver.jdbc.SQLServerDriver',
				url: 'jdbc:sqlserver://VA1-DSPLAPP049;databaseName=aopLogging',
				user: 'grailsdbm',
				password: 'grailsdbm'
		)
		
	}
	root {
		error "stdout"
	}
	info additivity: false, 'jdbcAppender': [ "grails2.example.performanceLogging" ]
	info additivity: false, 'stdout': [ "grails2.example.performanceLogging" ]
}
