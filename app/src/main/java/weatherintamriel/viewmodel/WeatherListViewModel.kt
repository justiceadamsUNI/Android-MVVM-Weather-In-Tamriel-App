package weatherintamriel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Handler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import weatherintamriel.api.WeatherRepository
import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel
import weatherintamriel.model.map.CurrentWeatherResultToCurrentWeatherModelMapper
import weatherintamriel.model.map.ForecastResultToForecastModelMapper

class WeatherListViewModel(private val weatherRepository: WeatherRepository):
        ViewModel() {

    val viewstate = MutableLiveData<WeatherListViewState>()
    private val forecastResultToForecastModelMapper = ForecastResultToForecastModelMapper()
    private val currentWeatherResultToCurrentWeatherModelMapper
            = CurrentWeatherResultToCurrentWeatherModelMapper()

    init {
        viewstate.value = WeatherListViewState(
                forecasts = emptyList(),
                currentWeather = null,
                showingProgressSpinner = false)

        showProgressSpinner()

        // This is here simply to demonstrate ViewModel updates and how they work.
        // It's also a little less jarring of a UI. If this was a production
        // application it definitely would not be here.
        Handler().postDelayed({
            getForecast()
            getCurrentWeather()
        }, 3000)
    }

    private fun getForecast(){
        weatherRepository.getForecasts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<ForecastModel>> {
                    forecastResultToForecastModelMapper.convertToListOfModels(it)
                }
                .subscribe(::showForecasts)
    }

    private fun getCurrentWeather(){
        weatherRepository.getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { currentWeatherResultToCurrentWeatherModelMapper.convertToModel(it) }
                .subscribe(::showCurrentWeather)
    }

    fun showForecasts(forecasts: List<ForecastModel>) {
        //ToDo: wrap mutable live data in a class to ensure that
        // the view state is never null. The current implementation
        // of LiveData is gross and should account for this.
        viewstate.value =
                viewstate.value?.copy(forecasts = forecasts, showingProgressSpinner = false)
    }

    fun showCurrentWeather(currentWeather: CurrentWeatherModel) {
        viewstate.value =
                viewstate.value?.copy(currentWeather = currentWeather, showingProgressSpinner = false)
    }

    fun showProgressSpinner() {
        viewstate.value = viewstate.value?.copy(showingProgressSpinner = true)
    }

    class Factory(private val weatherRepository: WeatherRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T
                = WeatherListViewModel(weatherRepository = weatherRepository) as T
    }
}
