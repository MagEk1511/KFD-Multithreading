package transactions

import Bank
import Client

class FundTransferWithExchange (val sender: Client, val receiver: Client, val amount: Double, val bank: Bank): Transaction() {
    override fun make(): Status {
        if (sender.amount < amount) {
            status = Status.ERROR
            message = if (sender.currency != receiver.currency) {
                "Different sender and receiver currency"
            } else {
                "Insufficient funds in the account"
            }
        } else {
            synchronized(sender) {
                val newAmount = bank.currencyHandler.exchange(sender.currency, receiver.currency, amount)
                receiver.amount += newAmount
                sender.amount -= amount
            }
            status = Status.COMPLETED
            message = "Completed transaction"
        }
        return status
    }
}