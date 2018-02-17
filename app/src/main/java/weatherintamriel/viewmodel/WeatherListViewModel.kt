package weatherintamriel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import weatherintamriel.api.WeatherRepository
import weatherintamriel.model.CurrentWeatherModel
import weatherintamriel.model.ForecastModel

class WeatherListViewModel(private val weatherRepository: WeatherRepository):
        ViewModel(), WeatherRepository.Callback {

    val viewstate = MutableLiveData<WeatherListViewState>()

    private fun getForecast(){
        weatherRepository.getForecasts(this)
    }

    private fun getCurrentWeather(){
        weatherRepository.getCurrentWeather(this)
    }

    override fun onForecastLoaded(forecasts: List<ForecastModel>) {
        //ToDo: wrap mutable live data in a class to ensure that
        // the view state is never null. The current implementation
        // of LiveData is gross and should account for this.
        viewstate.value = viewstate.value?.copy(forecasts = forecasts)
    }

    override fun onCurrentWeatherLoaded(currentWeather: CurrentWeatherModel) {
        viewstate.value = viewstate.value?.copy(currentWeather = currentWeather)
    }

    class Factory(private val weatherRepository: WeatherRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T
                = WeatherListViewModel(weatherRepository = weatherRepository) as T
    }
}
