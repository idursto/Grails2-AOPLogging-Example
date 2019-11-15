import grails2.example.Coin
import grails2.example.CoinType

class BootStrap {

    def init = { servletContext ->
        new Coin( id: "1", type: CoinType.Penny, year: 2019, value: 1 ).save( flush: true)
        new Coin( id: "2", type: CoinType.Nickel, year: 2019, value: 5 ).save( flush: true)
        new Coin( id: "3", type: CoinType.Dime, year: 2019, value: 10 ).save( flush: true)
    }
    def destroy = {
    }
}
