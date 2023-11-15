import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

class Bank {

    val currencyHandler = CurrencyHandler()

    val clientMap: ConcurrentHashMap<Int, Client> = ConcurrentHashMap<Int, Client>()

    private val cashiers = ArrayList<Cashier>()


    val transactionsQueue: ConcurrentLinkedQueue<Transaction> = ConcurrentLinkedQueue<Transaction>()

    fun addCashier(cashier: Cashier) {
        cashiers.add(cashier)
    }

}