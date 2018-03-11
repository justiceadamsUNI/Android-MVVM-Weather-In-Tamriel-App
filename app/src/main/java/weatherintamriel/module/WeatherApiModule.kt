package weatherintamriel.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import weatherintamriel.api.WeatherApi
import javax.inject.Singleton

@Module
class WeatherApiModule {
    private val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient())
                .build()

        return retrofit.create(WeatherApi::class.java)
    }
}