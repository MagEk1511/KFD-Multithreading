import transactions.Status
import transactions.Transaction
import java.util.concurrent.ConcurrentLinkedQueue

class Cashier(private val bank: Bank, private val id: Int): Thread() {
    private val completedTransactionQueue: ConcurrentLinkedQueue<Transaction> = ConcurrentLinkedQueue<Transaction>()

    @Volatile
    private var isInterrupted = false

    override fun run() {
        while (!isInterrupted) {
            if (isInterrupted) {
                break
            }

            val transaction = try {
                bank.transactionsQueue.poll()
            } catch (e: InterruptedException) {
                // Обработка прерывания
                isInterrupted = true
                null
            }

            if (transaction != null) {
                transaction.make()
                bank.notifyObservers(transaction.status, transaction.message)
                completedTransactionQueue.add(transaction)
            }
        }
    }

    fun shutdown() {
        isInterrupted = true
    }

    init {
        this.start()
    }

}