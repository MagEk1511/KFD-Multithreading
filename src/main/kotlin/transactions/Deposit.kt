package transactions

import Client

class Deposit(val client: Client, val amount: Double): Transaction() {
    override fun make(): Status {
        synchronized(client) {
            client.amount += amount
        }
        status = Status.COMPLETED
        message = "Completed transaction"
        return status
    }
}