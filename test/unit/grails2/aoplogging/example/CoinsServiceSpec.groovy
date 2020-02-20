package grails2.aoplogging.example


import grails.test.mixin.TestFor
import grails2.example.services.CoinsService
import spock.lang.Specification 
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor( CoinsService)
class CoinsServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
	    when:
	    10000.times {
		    def get = new URL( "http://localhost:8080/Grails2-AOPLogging-Example/coins/index/" ).openConnection();
		    def getRC = get.getResponseCode();
		    println( getRC );
		    if ( !getRC.equals( 200 ) ) {
			    println( "ERROR!...${getRC}" );
		    }
	    }
	    
	    then:
	    true == true
    }
}
