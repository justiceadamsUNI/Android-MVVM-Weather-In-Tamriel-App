package weatherintamriel

import android.app.Application
import weatherintamriel.component.DaggerWeatherListViewComponent
import weatherintamriel.component.WeatherListViewComponent

class WeatherInTamrielApplication: Application() {
    private val weatherListViewComponent: WeatherListViewComponent =
            DaggerWeatherListViewComponent
                    .builder()
                    .build()

    fun getWeatherListViewComponent(): WeatherListViewComponent {
        return weatherListViewComponent
    }
}