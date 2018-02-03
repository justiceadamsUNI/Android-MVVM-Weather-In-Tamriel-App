package weatherintamriel.model.map

import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.CurrentWeatherResult
import java.text.DateFormat
import java.util.*

class CurrentWeatherResultToCurrentWeatherModelMapper {

    fun convertToModel(weather: CurrentWeatherResult): CurrentWeatherModel {
        val weatherResult = weather.weather[0]

        return CurrentWeatherModel(
                getDate(weather),
                weatherResult.description,
                weather.main.temp,
                weather.main.temp_min,
                weather.main.temp_max,
                weather.main.humidity,
                generateIconUrl(weatherResult.icon))
    }

    private fun getDate(currentWeatherResult: CurrentWeatherResult): String {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        // Api returns Unix_time so we multiply by 1000 to get milliseconds
        return dateFormat.format(currentWeatherResult.dt * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}