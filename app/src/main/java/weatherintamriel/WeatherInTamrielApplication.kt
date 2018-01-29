package weatherintamriel

import android.app.Application
import weatherintamriel.component.DaggerWeatherRepositoryComponent
import weatherintamriel.component.WeatherRepositoryComponent

class WeatherInTamrielApplication: Application() {
    private val weatherRepositoryComponent: WeatherRepositoryComponent =
            DaggerWeatherRepositoryComponent
                    .builder()
                    .build()

    fun getWeatherRepositoryComponent(): WeatherRepositoryComponent {
        return weatherRepositoryComponent
    }
}