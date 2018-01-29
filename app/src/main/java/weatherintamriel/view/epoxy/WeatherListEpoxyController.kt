package weatherintamriel.view.epoxy

import com.airbnb.epoxy.EpoxyController
import weatherintamriel.model.ForecastModel
import weatherintamriel.model.ForecastRequestResult

class WeatherListEpoxyController: EpoxyController() {
    private var data = listOf<ForecastModel>()


    fun setData(data: List<ForecastModel>) {
        this.data = data

        requestModelBuild()
    }

    override fun buildModels() {
        //The id is the date since we only display one model per day!
        add(data.map { WeatherListEpoxyModel(it).apply { id(it.date) } })
    }

}