package weatherintamriel.module

import dagger.Module
import dagger.Provides
import weatherintamriel.api.repository.WeatherRepository
import weatherintamriel.api.repository.ZipCodeInformationRepository
import weatherintamriel.viewmodel.WeatherListViewModel
import javax.inject.Singleton

@Module
class WeatherListViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideWeatherListViewModelFactory(weatherRepository: WeatherRepository,
                                           zipCodeInformationRepository: ZipCodeInformationRepository)
            = WeatherListViewModel.Factory(
            weatherRepository = weatherRepository,
            zipCodeInformationRepository = zipCodeInformationRepository
    )
}
