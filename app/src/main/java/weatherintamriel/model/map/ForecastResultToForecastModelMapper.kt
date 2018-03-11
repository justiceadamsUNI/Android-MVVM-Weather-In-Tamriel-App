package weatherintamriel.model.map

import android.text.format.DateUtils
import weatherintamriel.model.ForecastModel
import weatherintamriel.model.ForecastRequestResult
import weatherintamriel.model.ForecastResult
import weatherintamriel.util.convertDateToTamrielDate
import weatherintamriel.util.iconCodeToImageUrl
import java.util.*

class ForecastResultToForecastModelMapper {

    fun convertToListOfModels(forecast: ForecastRequestResult): List<ForecastModel> {
        return convertForecastListToDomain(
                // Api returns Unix_time so we multiply by 1000 to get milliseconds
                forecast.list.filter {
                    !Date(it.dt * 1000).before(Date())
                            && !DateUtils.isToday(it.dt * 1000)
                })
    }

    private fun convertForecastListToDomain(list: List<ForecastResult>): List<ForecastModel> {
        return list.map { forecast ->
            convertForecastItemToDomain(forecast)
        }
    }

    private fun convertForecastItemToDomain(forecast: ForecastResult): ForecastModel {
        return ForecastModel(
                convertDateToTamrielDate(forecast.dt),
                forecast.weather[0].description,
                forecast.temp.max,
                forecast.temp.min,
                iconCodeToImageUrl(forecast.weather[0].icon))
    }
}