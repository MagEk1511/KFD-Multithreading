import java.util.Currency
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.security.auth.kerberos.KerberosCredMessage

class CurrencyHandler {
    private val exchangeRateTable = ConcurrentHashMap<Currency, Double>()

    private val updater = ExchangeRateUpdater()

    init {
        val executor = ScheduledThreadPoolExecutor(1)
        executor.scheduleAtFixedRate(
            {
                update()
                println(exchangeRateTable)
            }, 0, 1, TimeUnit.MINUTES
        )
    }

    private fun update() {
        synchronized(Any()){
            updater.getExchangingRate().rates.forEach { (key, value) ->
                try {
                    exchangeRateTable[Currency.getInstance(key)] = value
                } catch (exc: IllegalArgumentException) {
//                    println(key)
                    TODO("Добавить логирование")
                }
            }
        }
    }

    fun exchange(toCurrency: Currency, amount: Double): Double =
        exchangeRateTable[toCurrency]!! * amount

    fun exchange(fromCurrency: Currency, toCurrency: Currency, amount: Double): Double =
        exchangeRateTable[toCurrency]!! / exchangeRateTable[fromCurrency]!! * amount

    fun exchange(toCurrency: String, amount: Double): Double {
        try {
            return exchange(Currency.getInstance(toCurrency), amount)
        } catch (exc: IllegalArgumentException) {
            println("Incorrect currency code")
            throw exc
        }

    }

    fun exchange(fromCurrency: String, toCurrency: String, amount: Double): Double {
        try {
            return exchange(Currency.getInstance(fromCurrency), Currency.getInstance(toCurrency), amount)
        } catch (exc: IllegalArgumentException) {
            println("Incorrect currency code")
            throw exc
        }
    }
}

class WrongCurrencyException: Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)

}