package weatherintamriel.module

import weatherintamriel.api.WeatherApi
import weatherintamriel.api.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherForecastRepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(weatherApi: WeatherApi): WeatherForecastRepository {
        return WeatherForecastRepository(weatherApi)
    }
}