package weatherintamriel.viewmodel

import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel

data class WeatherListViewState(val forecasts: List<ForecastModel>,
                                val currentWeather: CurrentWeatherModel?,
                                val showingProgressSpinner: Boolean,
                                val zipCode: Int?)