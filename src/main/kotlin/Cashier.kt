import transactions.Status
import transactions.Transaction
import java.util.concurrent.ConcurrentLinkedQueue

class Cashier(private val bank: Bank, private val id: Int): Thread() {
    private val completedTransactionQueue: ConcurrentLinkedQueue<Transaction> = ConcurrentLinkedQueue<Transaction>()

    override fun run() {
        while(true) {
            val transaction = bank.transactionsQueue.poll()
            if (transaction != null) {
                if(transaction.make() == Status.ERROR) {
                    println("$id [${transaction.status}]: ${transaction.message}")
//                    TODO("Add logging")
                } else {
                    println("$id [${transaction.status}]: ${transaction.message}")
//                    TODO("Add logging")
                }
                completedTransactionQueue.add(transaction)
            }
        }
    }

    init {
        this.start()
    }

}