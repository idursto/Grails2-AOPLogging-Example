package grails2.example.services

import grails.transaction.Transactional
import grails2.example.Coin

@Transactional
class CoinsService {

    List<Coin> getAllCoins() {
        return Coin.getAll()
    }
}
