package grails2.example

import grails.rest.RestfulController

class CoinsController extends RestfulController<Coin> {
    static responseFormats = ['json', 'xml']

    CoinsController() {
        super(Coin)
    }

    def index() {
        super.index()
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
