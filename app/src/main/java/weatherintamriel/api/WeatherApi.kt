package weatherintamriel.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import weatherintamriel.model.CurrentWeatherResult
import weatherintamriel.model.ForecastRequestResult
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val MODE = "json"
        private const val UNITS = "imperial"
        private const val COUNT = "7"
    }

    @GET("forecast/daily?APPID=$APP_ID&mode=$MODE&units=$UNITS&cnt=$COUNT")
    fun getForecastForZipCode(@Query("zip") zipCode: Int): Single<ForecastRequestResult>

    @GET("weather?APPID=$APP_ID&mode=$MODE&units=$UNITS")
    fun getCurrentWeatherForZipCode(@Query("zip") zipCode: Int): Single<CurrentWeatherResult>
}