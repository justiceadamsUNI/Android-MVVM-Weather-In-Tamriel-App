package weatherintamriel.model

data class ForecastRequestResult(val city: CityResult, val list: List<ForecastResult>)

data class CityResult(val id: Long, val name: String, val coord: CoordinatesResult,
                      val country: String, val population: Int)

data class CoordinatesResult(val lon: Float, val lat: Float)

data class ForecastResult(val dt: Long, val temp: TemperatureResult, val pressure: Float,
                          val humidity: Int, val weather: List<WeatherResult>,
                          val speed: Float, val deg: Int, val clouds: Int,
                          val rain: Float)

data class TemperatureResult(val day: Float, val min: Float, val max: Float,
                             val night: Float, val eve: Float, val morn: Float)

data class WeatherResult(val id: Long, val main: String, val description: String,
                         val icon: String)

data class CurrentWeatherResult(val weather: List<WeatherResult>, val main: MainResult)

data class MainResult(val temp: Float,
                val temp_min: Float,
                val temp_max: Float,
                val humidity: Int)