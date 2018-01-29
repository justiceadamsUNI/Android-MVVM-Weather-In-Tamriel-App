package weatherintamriel.component

import weatherintamriel.api.WeatherApi
import dagger.Component
import weatherintamriel.module.WeatherApiModule
import weatherintamriel.module.WeatherForecastRepositoryModule
import weatherintamriel.view.WeatherListActivity
import javax.inject.Singleton

@Component(modules = [(WeatherApiModule::class), (WeatherForecastRepositoryModule::class)])

@Singleton
interface WeatherRepositoryComponent {
    fun weatherApi(): WeatherApi

    fun inject(activity: WeatherListActivity)
}