import java.util.Currency
import java.util.concurrent.ConcurrentHashMap

class CurrencyHandler {
    private val exchangeRateTable = ConcurrentHashMap<Currency, Double>()

    private val updater = ExchangeRateUpdater()

    init {
        updater.getExchangingRate().rates.forEach { (key, value) ->
            try {
                exchangeRateTable[Currency.getInstance(key)] = value
            } catch (exc: IllegalArgumentException) {
                println(key)
                // Добавить логирование
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

    // Добавить автоматическое обновление курса
}