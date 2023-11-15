import java.util.Currency
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class CurrencyHandler {
    private val exchangeRateTable = ConcurrentHashMap<Currency, Double>()

    private val updater = ExchangeRateUpdater()

    private fun update() {
        val rates = updater.getExchangingRate().rates
        synchronized(Any()) {
        rates.forEach { (key, value) ->
            try {
                val curr = Currency.getInstance(key)
                exchangeRateTable[curr] = value
            } catch (ex: IllegalArgumentException) {
                println("Wrong currency: $key")
                }
            TODO("Add logging")
            }
        }
    }

    init {
        val executor = ScheduledThreadPoolExecutor(1)
            executor.scheduleAtFixedRate(
                {
                    update()
                }, 0, 30, TimeUnit.SECONDS
            )

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