package transactions

import Client
import java.util.Currency

class ChangeAccountCurrency(val client: Client, val toCurrency: Currency, val exchangeRate: Double): Transaction() {
    override fun make(): Status {
        synchronized(client) {
            client.currency = toCurrency
            client.amount *= exchangeRate
            status = Status.COMPLETED
        }
        return status
    }
}