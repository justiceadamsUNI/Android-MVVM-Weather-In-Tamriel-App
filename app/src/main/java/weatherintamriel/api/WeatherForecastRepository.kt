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

    fun getForecastAndCurrentWeather(onDataLoaded: (List<ForecastModel>,
                                                    CurrentWeatherModel) -> Unit){
        doAsync {
            val forecastList = weatherApi
                    .getForecastForZipCode(50613) //ToDo: Make this dynamic
                    .execute()
                    .body()

            val weather = weatherApi
                    .getCurrentWeatherForZipCode(50613) //ToDo: Make this dynamic
                    .execute()
                    .body()

            uiThread {
                val currentWeatherModel: CurrentWeatherModel = weather
                        ?.let{ CurrentWeatherResultToCurrentWeatherModelMapper()
                                .convertToModel(weather) }
                        ?: throw Exception()

                val forecastModelList = forecastList
                        ?.let{ ForecastResultToForecastModelMapper()
                                .convertToModel(forecastList)}
                        .orEmpty()

                onDataLoaded(forecastModelList, currentWeatherModel)
            }
        }
    }
}