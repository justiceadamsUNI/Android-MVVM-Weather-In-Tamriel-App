package weatherintamriel.module

import dagger.Module
import dagger.Provides
import weatherintamriel.api.WeatherRepository
import weatherintamriel.viewmodel.WeatherListViewModel
import javax.inject.Singleton

@Module
class WeatherListViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideWeatherListViewModelFactory(weatherRepository: WeatherRepository)
            = WeatherListViewModel.Factory(weatherRepository = weatherRepository)
}
