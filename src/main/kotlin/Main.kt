import transactions.Deposit
import transactions.FundTransfer
import transactions.Withdraw
import java.util.*

fun main() {
    val bank = Bank()
    bank.clientMap[0] = Client(0, 100.0, Currency.getInstance("USD"))
    bank.clientMap[1] = Client(1, 0.0, Currency.getInstance("USD"))
    bank.addCashier(Cashier(bank, 1))
    bank.addCashier(Cashier(bank, 2))
    bank.transactionsQueue.add(FundTransfer(bank.clientMap[0]!!, bank.clientMap[1]!!, 50.0))
    bank.transactionsQueue.add(FundTransfer(bank.clientMap[0]!!, bank.clientMap[1]!!, 50.0))
    bank.transactionsQueue.add(FundTransfer(bank.clientMap[0]!!, bank.clientMap[1]!!, 50.0))
    bank.transactionsQueue.add(Deposit(bank.clientMap[0]!!, 5000.0))
    bank.transactionsQueue.add(Withdraw(bank.clientMap[1]!!, 100.0))


}