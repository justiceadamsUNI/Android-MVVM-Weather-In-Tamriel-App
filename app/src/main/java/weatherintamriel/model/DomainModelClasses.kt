package weatherintamriel.model

data class ForecastModel(val date: String,
                         val description: String,
                         val high: Int,
                         val low: Int,
                         val iconUrl: String)

data class CurrentWeatherModel(val date: String,
                               val description: String,
                               val temp: Int,
                               val temp_min: Int,
                               val temp_max: Int,
                               val humidity: Int,
                               val iconUrl: String)

data class LocationInfoModel(val locationInfo: String)