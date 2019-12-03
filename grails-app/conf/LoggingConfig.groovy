// log4j configuration
log4j.main = {
	appenders {
		console name: "stdout", layout: pattern( conversionPattern: '[%p] %d [%t] %x %c %M - %m%n' )
	}
	root {
		error "stdout"
	}
	info additivity: false, stdout: [ "grails2.example" ]
}
