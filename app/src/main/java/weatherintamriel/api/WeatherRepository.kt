package weatherintamriel.api

import io.reactivex.Single
import weatherintamriel.model.CurrentWeatherResult
import weatherintamriel.model.ForecastRequestResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private var weatherApi: WeatherApi) {

    fun getForecasts(): Single<ForecastRequestResult> =
            weatherApi.getForecastForZipCode(50613) //ToDo: Make this dynamic

    fun getCurrentWeather(): Single<CurrentWeatherResult> =
            weatherApi.getCurrentWeatherForZipCode(50613) // ToDo: make this dynamic
}