package weatherintamriel.model

import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ForecastDataMapper {

    fun convertToModel(forecast: ForecastRequestResult): List<ForecastModel> {
        return convertForecastListToDomain(forecast.list)
    }

    private fun convertForecastListToDomain(list: List<ForecastResult>): List<ForecastModel> {
        return list.mapIndexed { i, forecast ->
            val date = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = date))
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