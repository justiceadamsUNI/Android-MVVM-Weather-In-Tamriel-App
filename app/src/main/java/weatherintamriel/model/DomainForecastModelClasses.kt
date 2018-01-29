package weatherintamriel.model

data class ForecastModel(val date: String,
                    val description: String,
                    val high: Float,
                    val low: Float,
                    val iconUrl: String)