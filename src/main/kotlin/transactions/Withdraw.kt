package transactions

import Client

class Withdraw(val client: Client, var amount: Double): Transaction() {
    override fun make(): Status {
        if (client.amount < amount) {
            status = Status.ERROR
            message = "Lack of funds"
        } else {
            synchronized(client) {
                client.amount -= amount
            }
            status = Status.COMPLETED
            message = "Completed transaction"
        }
        return status
    }
}