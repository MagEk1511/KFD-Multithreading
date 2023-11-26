package transactions

abstract class Transaction() {
    var status: Status = Status.NEW
    var message: String = "New transaction"

    abstract fun make(): Status
}