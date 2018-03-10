package weatherintamriel.module

import dagger.Module
import dagger.Provides
import weatherintamriel.api.WeatherApi
import weatherintamriel.api.repository.WeatherRepository
import javax.inject.Singleton

@Module
class WeatherRepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepository(weatherApi)
    }
}