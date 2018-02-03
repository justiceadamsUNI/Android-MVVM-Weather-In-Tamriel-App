package weatherintamriel.model.map

import weatherintamriel.model.ForecastModel
import weatherintamriel.model.ForecastRequestResult
import weatherintamriel.model.ForecastResult
import java.text.DateFormat
import java.util.*

class ForecastResultToForecastModelMapper {

    fun convertToModel(forecast: ForecastRequestResult): List<ForecastModel> {
        return convertForecastListToDomain(forecast.list)
    }

    private fun convertForecastListToDomain(list: List<ForecastResult>): List<ForecastModel> {
        return list.map { forecast ->
            // Api returns Unix_time so we multiply by 1000 to get milliseconds
            convertForecastItemToDomain(forecast.copy(dt = forecast.dt * 1000))
        }
    }

    private fun convertForecastItemToDomain(forecast: ForecastResult): ForecastModel {
        return ForecastModel(
                convertDate(forecast.dt),
                forecast.weather[0].description,
                forecast.temp.max,
                forecast.temp.min,
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}