package weatherintamriel.component

import dagger.Component
import weatherintamriel.module.WeatherApiModule
import weatherintamriel.module.WeatherListViewModelFactoryModule
import weatherintamriel.module.WeatherRepositoryModule
import weatherintamriel.module.ZipCodeApiModule
import weatherintamriel.view.WeatherListActivity
import javax.inject.Singleton

@Component(modules = [
(WeatherApiModule::class),
(WeatherRepositoryModule::class),
(ZipCodeApiModule::class),
(ZipCodeApiModule::class),
(WeatherListViewModelFactoryModule::class)])

@Singleton
interface WeatherListViewComponent {
    fun inject(activity: WeatherListActivity)
}