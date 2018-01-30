package weatherintamriel.model.map

import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.CurrentWeatherResult
import java.text.DateFormat
import java.util.*

class CurrentWeatherResultToCurrentWeatherModelMapper {

    fun convertToModel(weather: CurrentWeatherResult): CurrentWeatherModel {
        val weatherResult = weather.weather[0]

        return CurrentWeatherModel(
                convertDate(weather.dt),
                weatherResult.description,
                weather.main.temp,
                weather.main.temp_min,
                weather.main.temp_max,
                weather.main.humidity,
                generateIconUrl(weatherResult.icon))
    }

    private fun convertDate(date: Long): String {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}