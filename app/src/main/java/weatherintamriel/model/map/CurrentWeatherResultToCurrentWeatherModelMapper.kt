package weatherintamriel.model.map

import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.CurrentWeatherResult
import weatherintamriel.util.convertDateToTamrielDate
import weatherintamriel.util.iconCodeToImageUrl

class CurrentWeatherResultToCurrentWeatherModelMapper {

    fun convertToModel(weather: CurrentWeatherResult): CurrentWeatherModel {
        val weatherResult = weather.weather[0]

        return CurrentWeatherModel(
                convertDateToTamrielDate(weather.dt),
                weatherResult.description,
                weather.main.temp,
                weather.main.temp_min,
                weather.main.temp_max,
                weather.main.humidity,
                iconCodeToImageUrl(weatherResult.icon))
    }
}