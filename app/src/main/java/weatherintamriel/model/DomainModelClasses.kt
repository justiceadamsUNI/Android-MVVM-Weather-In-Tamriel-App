package weatherintamriel.model

data class ForecastModel(val date: String,
                         val description: String,
                         val high: Float,
                         val low: Float,
                         val iconUrl: String)

data class CurrentWeatherModel(val date: String,
                               val description: String,
                               val temp: Float,
                               val temp_min: Float,
                               val temp_max: Float,
                               val humidity: Int,
                               val iconUrl: String)

data class LocationInfoModel(val locationInfo: String)