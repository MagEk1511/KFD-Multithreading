import com.google.gson.Gson
import java.net.URL

data class ExchangeRate (val success: Boolean, val timestamp: Int, val base: String,
                    val date: String, val rates: HashMap<String, Double>)

class ExchangeRateUpdater {

    private val gson = Gson()

    fun getExchangingRate(): ExchangeRate {
        val json = URL("http://api.exchangeratesapi.io/v1/latest?access_key=07af2429a3c0f8a05dab91dc940bb07b").readText()
        return gson.fromJson(json, ExchangeRate::class.java)
    }
}
