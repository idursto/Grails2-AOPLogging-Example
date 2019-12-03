import grails2.example.Coin
import grails2.example.CoinType

databaseChangeLog = {

	changeSet(author: "idurston (generated)", id: "1575408637439-1") {
		createTable(schemaName: "dbo", tableName: "coin") {
			column(autoIncrement: "true", name: "id", type: "NUMERIC(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "PK__coin__3213E83F2794B54F")
			}

			column(name: "version", type: "NUMERIC(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "year", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "idurston (generated)", id: "1575408637439-2") {
		createTable(schemaName: "dbo", tableName: "logging") {
			column(autoIncrement: "true", name: "id", type: "NUMERIC(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "PK__logging__3213E83FE471E2FD")
			}

			column(name: "version", type: "NUMERIC(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "application", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "class_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "duration_ms", type: "NUMERIC(19,0)")

			column(name: "method", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "idurston (generated)", id: "1575408637439-3") {
		grailsChange {
			change {
				new Coin( id: "1", type: CoinType.Penny, year: 2019, value: 1 ).save( flush: true)
				new Coin( id: "2", type: CoinType.Nickel, year: 2019, value: 5 ).save( flush: true)
				new Coin( id: "3", type: CoinType.Dime, year: 2019, value: 10 ).save( flush: true)
			}
		}
	}
}
