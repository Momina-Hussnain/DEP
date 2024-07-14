package 2;

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var etCity: EditText
    private lateinit var btnGetWeather: Button
    private lateinit var tvWeather: TextView
    private val apiService = ApiService.create()
    private val apiKey = "YOUR_API_KEY" // Replace with your OpenWeatherMap API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCity = findViewById(R.id.etCity)
        btnGetWeather = findViewById(R.id.btnGetWeather)
        tvWeather = findViewById(R.id.tvWeather)

        btnGetWeather.setOnClickListener {
            val city = etCity.text.toString().trim()
            if (city.isNotEmpty()) {
                getWeather(city)
            }
        }
    }

    private fun getWeather(city: String) {
        lifecycleScope.launch {
            try {
                val response = apiService.getWeather(city, apiKey)
                val weatherDescription = response.weather[0].description
                val temperature = response.main.temp
                tvWeather.text = "Weather in ${response.name}: $weatherDescription, ${temperature}°C"
            } catch (e: IOException) {
                tvWeather.text = "Network error: ${e.message}"
            } catch (e: HttpException) {
                tvWeather.text = "HTTP error: ${e.message}"
            }
        }
    }
}
