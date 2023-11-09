import java.util.LinkedList
import java.util.concurrent.ConcurrentHashMap

class Bank {

    val currencyHandler = CurrencyHandler()

    val clientMap: ConcurrentHashMap<Int, Client> = ConcurrentHashMap<Int, Client>()

    private val cashiers = ArrayList<Cashier>()


//    val transactionsQueue: LinkedList<Transactions> = LinkedList<Transactions>()

    fun addCashier(cashier: Cashier) {
        cashiers.add(cashier)
    }

}