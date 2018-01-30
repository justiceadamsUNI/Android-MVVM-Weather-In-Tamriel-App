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

    fun getForecast(onForecastLoaded: (List<ForecastModel>) -> Unit){
        doAsync {
            val weather = weatherApi
                    .getForecastForZipCode(50613) //ToDo: Make this dynamic
                    .execute()
                    .body()

            uiThread {
                onForecastLoaded(weather
                                ?.let{ ForecastResultToForecastModelMapper()
                                        .convertToModel(weather)}
                                .orEmpty())
            }
        }
    }

    fun getCurrentWeather(onCurrentWeatherLoaded: (CurrentWeatherModel) -> Unit){
        doAsync {
            val weather = weatherApi
                    .getCurrentWeatherForZipCode(50613) //ToDo: Make this dynamic
                    .execute()
                    .body()

            uiThread {
                onCurrentWeatherLoaded(weather
                        ?.let{ CurrentWeatherResultToCurrentWeatherModelMapper()
                                .convertToModel(weather) }
                        ?: throw Exception())

            }
        }
    }
}