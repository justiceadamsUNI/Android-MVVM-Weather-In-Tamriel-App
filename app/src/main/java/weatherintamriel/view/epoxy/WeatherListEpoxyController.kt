package weatherintamriel.view.epoxy

import com.airbnb.epoxy.EpoxyController
import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel

class WeatherListEpoxyController: EpoxyController() {
    private var forecastList = listOf<ForecastModel>()
    private var currentWeather: CurrentWeatherModel? = null


    fun setData(forecastList: List<ForecastModel>, currentWeather: CurrentWeatherModel?) {
        this.forecastList = forecastList
        this.currentWeather = currentWeather

        requestModelBuild()
    }

    override fun buildModels() {
        //The id is the date since we only display one model per day!
        val currentWeather = this.currentWeather
        currentWeather?.let { add(CurrentWeatherEpoxyModel(currentWeather).apply { id(it.date) }) }

        add(forecastList.map { ForecastRowEpoxyModel(it).apply { id(it.date) } })
    }
}