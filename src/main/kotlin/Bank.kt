import logging.Observer
import transactions.Status
import transactions.Transaction
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

class Bank {

    val currencyHandler = CurrencyHandler()

    private val clientMap: ConcurrentHashMap<Int, Client> = ConcurrentHashMap<Int, Client>()

    private val cashiers = ArrayList<Cashier>()

    val transactionsQueue: ConcurrentLinkedQueue<Transaction> = ConcurrentLinkedQueue<Transaction>()

    private val observers = mutableListOf<Observer>()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun notifyObservers(status: Status, message: String) {
        observers.forEach {
            it.update(status, message)
        }
    }

    fun addCashier(cashier: Cashier) {
        cashiers.add(cashier)
    }

    fun printClientInfo() {
        clientMap.forEach {
            (_, value) ->
            println(value)
        }
    }

    fun printCashierInfo() {
        cashiers.forEach {
            println(it)
        }
    }

    fun endCashiers() {
        cashiers.forEach {
            it.shutdown()
            it.interrupt()
        }
    }



    fun addClient(client: Client) {
        clientMap[client.clientId] = client
    }

    fun getClient(id: Int): Client? {
        return clientMap[id]
    }

}