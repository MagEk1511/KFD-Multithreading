import java.util.LinkedList
import java.util.concurrent.ConcurrentLinkedQueue

class Cashier(private val bank: Bank): Thread() {
    private val completedTransactionQueue: ConcurrentLinkedQueue<Transaction> = ConcurrentLinkedQueue<Transaction>()

    override fun run() {
        while(true) {
            val transaction = bank.transactionsQueue.poll()
            if (transaction != null) {
                transaction.make()
                completedTransactionQueue.add(transaction)
            }
            TODO("Add logging")
            TODO("Add exceptions")
        }
    }

    init {
        this.start()
    }

}