import java.time.temporal.TemporalAmount

abstract class Transaction() {
    abstract fun generate(): Cashier.() -> Status

}

class FundTransferGenerator(private val fromClient: Client, private val toClient: Client, private val amount: Double): Transaction() {
    override fun generate(): Cashier.() -> Status {
        if(fromClient.currency == toClient.currency){
            return {
                bank.clientMap[toClient.clientId]!!.amount += bank.clientMap[fromClient.clientId]!!.amount
                Status.SUCCESS
            }
        } else {
            throw WrongCurrencyException()
            TODO("Разобраться с исключением")
        }
    }

}

class ChangeAccountCurrencyGenerator(): Transaction() {
    override fun generate(): Cashier.() -> Status {
        TODO("Not yet implemented")
    }

}

class AccountReplenishGenerator(): Transaction() {
    override fun generate(): Cashier.() -> Status {
        TODO("Not yet implemented")
    }
}

class AccountDepositGenerator(): Transaction() {
    override fun generate(): Cashier.() -> Status {
        TODO("Not yet implemented")
    }

}

enum class Status {
    NEW, SUCCESS, ERROR
}