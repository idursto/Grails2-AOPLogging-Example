package grails2.example

class Coin {

    CoinType type

    int year
    
    int value
   
    static constraints = {
        type nullable: false
    }
}
