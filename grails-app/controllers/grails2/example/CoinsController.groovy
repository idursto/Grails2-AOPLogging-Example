package grails2.example

import grails.rest.RestfulController
import grails2.example.services.CoinsService 

class CoinsController extends RestfulController<Coin> {
    static responseFormats = ['json', 'xml']

    CoinsService coinsService

    CoinsController() {
        super(Coin)
    }

    def index() {
        respond coinsService.getAllCoins()
    }

    def show() {
        super.show()
    }

    def save() {
        super.save()
    }

    def update() {
        super.update()
    }

    def delete() {
        super.delete()
    }

    Object patch() {
        return super.patch()
    }
}
