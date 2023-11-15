import java.util.*

fun main() {
    val bank = Bank()
    bank.clientMap[0] = Client(0, 100.0, Currency.getInstance("USD"))
    bank.clientMap[1] = Client(1, 0.0, Currency.getInstance("USD"))
    bank.transactionsQueue.add(FundTransfer(bank.clientMap[0]!!, bank.clientMap[1]!!, 50.0))
    bank.addCashier(Cashier(bank))
}