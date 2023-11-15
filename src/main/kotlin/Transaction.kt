abstract class Transaction() {
    var status: Status = Status.NEW
    var message: String = "New transaction"

    abstract fun make(): Status
}

class FundTransfer(val sender: Client, val receiver: Client, val amount: Double): Transaction() {
    override fun make(): Status {
        if (sender.currency != receiver.currency || sender.amount < amount) {
            status = Status.ERROR
        } else {
            receiver.amount += amount
            sender.amount -= amount
            status = Status.COMPLETED
        }
        return status
    }
}

enum class Status {
    NEW, COMPLETED, ERROR
}