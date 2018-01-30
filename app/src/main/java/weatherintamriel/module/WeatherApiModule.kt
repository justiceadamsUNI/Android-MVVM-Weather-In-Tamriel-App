package weatherintamriel.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import weatherintamriel.api.WeatherApi
import javax.inject.Singleton

@Module
class WeatherApiModule {
    private val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(client)
                .build()

        return retrofit.create(WeatherApi::class.java)
    }
}