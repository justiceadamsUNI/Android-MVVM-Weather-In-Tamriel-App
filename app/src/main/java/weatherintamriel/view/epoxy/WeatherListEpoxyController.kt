package weatherintamriel.view.epoxy

import com.airbnb.epoxy.EpoxyController
import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel

class WeatherListEpoxyController: EpoxyController() {
    private var forecastList = listOf<ForecastModel>()
    private lateinit var currentWeather: CurrentWeatherModel


    fun setData(forecastList: List<ForecastModel>, currentWeather: CurrentWeatherModel) {
        this.forecastList = forecastList
        this.currentWeather = currentWeather

        requestModelBuild()
    }

    override fun buildModels() {
        //The id is the date since we only display one model per day!
        add(CurrentWeatherEpoxyModel(currentWeather).apply { id(-10) }) //ToDo: change so that we filter today out of other list. That way date can be id here.
        add(forecastList.map { ForecastListRowEpoxyModel(it).apply { id(it.date) } })
    }

}