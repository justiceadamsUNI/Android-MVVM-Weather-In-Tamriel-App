package weatherintamriel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
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
        viewstate.value = WeatherListViewState(forecasts = emptyList(), currentWeather = null)

        getForecast()
        getCurrentWeather()
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
        viewstate.value = viewstate.value?.copy(forecasts = forecasts)
    }

    fun showCurrentWeather(currentWeather: CurrentWeatherModel) {
        viewstate.value = viewstate.value?.copy(currentWeather = currentWeather)
    }

    class Factory(private val weatherRepository: WeatherRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T
                = WeatherListViewModel(weatherRepository = weatherRepository) as T
    }
}
