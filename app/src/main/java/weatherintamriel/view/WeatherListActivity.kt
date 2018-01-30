package weatherintamriel.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import justiceadams.com.weatherintamriel.R
import kotlinx.android.synthetic.main.activity_weather_list.*
import weatherintamriel.WeatherInTamrielApplication
import weatherintamriel.api.WeatherForecastRepository
import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel
import weatherintamriel.view.epoxy.WeatherListEpoxyController
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity() {

    @Inject lateinit var weatherForecastRepository: WeatherForecastRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as WeatherInTamrielApplication).getWeatherRepositoryComponent().inject(this)

        setContentView(R.layout.activity_weather_list)
        forecast_list.layoutManager = LinearLayoutManager(this)

        weatherForecastRepository.getForecast(::updateData)

        weatherForecastRepository.getCurrentWeather(::printCurrentWeather)
    }

    private fun updateData(forecasts: List<ForecastModel>){
        val controller = WeatherListEpoxyController()
        forecast_list.adapter = controller.adapter
        controller.setData(forecasts)
    }

    private fun printCurrentWeather(currentWeatherModel: CurrentWeatherModel){
        //ToDo: Update to plug into epoxy with new model class
        Log.d("Logging: ", currentWeatherModel.toString())
    }
}
