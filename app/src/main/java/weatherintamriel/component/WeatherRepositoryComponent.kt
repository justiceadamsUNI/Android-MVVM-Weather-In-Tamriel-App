package weatherintamriel.component

import dagger.Component
import weatherintamriel.api.WeatherApi
import weatherintamriel.module.WeatherApiModule
import weatherintamriel.module.WeatherRepositoryModule
import weatherintamriel.view.WeatherListActivity
import javax.inject.Singleton

@Component(modules = [(WeatherApiModule::class), (WeatherRepositoryModule::class)])

@Singleton
interface WeatherRepositoryComponent {
    fun weatherApi(): WeatherApi

    fun inject(activity: WeatherListActivity)
}