package transactions
import Client

class FundTransfer(val sender: Client, val receiver: Client, val amount: Double): Transaction() {
    override fun make(): Status {
        if (sender.currency != receiver.currency || sender.amount < amount) {
            status = Status.ERROR
            message = if (sender.currency != receiver.currency) {
                "Different sender and receiver currency"
            } else {
                "Insufficient funds in the account"
            }
        } else {
            synchronized(sender) {
                receiver.amount += amount
                sender.amount -= amount
            }
            status = Status.COMPLETED
            message = "Completed transaction"
        }
        return status
    }
}