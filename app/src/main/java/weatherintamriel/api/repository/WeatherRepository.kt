package weatherintamriel.api.repository

import io.reactivex.Single
import weatherintamriel.api.WeatherApi
import weatherintamriel.model.CurrentWeatherResult
import weatherintamriel.model.ForecastRequestResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private var weatherApi: WeatherApi) {

    fun getForecasts(zipCode: Int): Single<ForecastRequestResult> =
            weatherApi.getForecastForZipCode(zipCode)

    fun getCurrentWeather(zipCode: Int): Single<CurrentWeatherResult> =
            weatherApi.getCurrentWeatherForZipCode(zipCode)
}