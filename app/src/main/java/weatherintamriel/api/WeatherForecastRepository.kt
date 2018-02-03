package weatherintamriel.api

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel
import weatherintamriel.model.map.CurrentWeatherResultToCurrentWeatherModelMapper
import weatherintamriel.model.map.ForecastResultToForecastModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherForecastRepository {
    private var weatherApi: WeatherApi

    @Inject
    constructor(weatherApi: WeatherApi) {
        this.weatherApi = weatherApi
    }

    fun getForecasts(callback: Callback) {
        doAsync {
            val forecastList = weatherApi
                    .getForecastForZipCode(50613) //ToDo: Make this dynamic
                    .execute()
                    .body()

            uiThread {
                val forecastModelList = forecastList
                        ?.let{ ForecastResultToForecastModelMapper()
                                .convertToModel(forecastList)
                        }.orEmpty()

                callback.onForecastLoaded(forecastModelList)
            }
        }
    }

    fun getCurrentWeather(callback: Callback) {
        doAsync {
            val weather = weatherApi
                    .getCurrentWeatherForZipCode(50613) //ToDo: Make this dynamic
                    .execute()
                    .body()
            uiThread {
                val currentWeatherModel: CurrentWeatherModel = weather
                        ?.let {
                            CurrentWeatherResultToCurrentWeatherModelMapper()
                                    .convertToModel(weather)
                        } ?: throw Exception("Couldn't get current weather data!")

                callback.onCurrentWeatherLoaded(currentWeatherModel)
            }
        }
    }

    interface Callback {
        fun onForecastLoaded(forecasts: List<ForecastModel>)

        fun onCurrentWeatherLoaded(currentWeather: CurrentWeatherModel)
    }
}