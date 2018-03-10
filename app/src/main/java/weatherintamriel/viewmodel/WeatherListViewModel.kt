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
    private var onZipcodeSuccessfullyUpdatedListener: (zipCode: Int) -> Unit = {}
    private val forecastResultToForecastModelMapper = ForecastResultToForecastModelMapper()
    private val currentWeatherResultToCurrentWeatherModelMapper
            = CurrentWeatherResultToCurrentWeatherModelMapper()

    init {
        viewstate.value = WeatherListViewState(
                forecasts = emptyList(),
                currentWeather = null,
                showingProgressSpinner = false,
                zipCode = null,
                showErrorDialog = false)
    }

    fun updateZipCode(zipCode: Int) {
        viewstate.value = viewstate.value?.copy(zipCode = zipCode, showErrorDialog = false)

        showProgressSpinner()
        // This is here simply to demonstrate ViewModel updates and how they work.
        // It's also a little less jarring of a UI. If this was a production
        // application it definitely would not be here.
        Handler().postDelayed({
            getForecast(zipCode)
            getCurrentWeather(zipCode)
        }, 3000)
    }

    fun setOnZipcodeSuccessfullyUpdatedListener(onZipCodeEntered: (zipCode: Int) -> Unit) {
        onZipcodeSuccessfullyUpdatedListener = onZipCodeEntered
    }

    private fun getForecast(zipCode: Int){
        weatherRepository.getForecasts(zipCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<ForecastModel>> {
                    forecastResultToForecastModelMapper.convertToListOfModels(it)
                }
                .doOnSuccess { onZipcodeSuccessfullyUpdatedListener.invoke(zipCode) }
                .subscribe(::showForecasts, ::showErrorDialog)
    }

    private fun getCurrentWeather(zipCode: Int){
        weatherRepository.getCurrentWeather(zipCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { currentWeatherResultToCurrentWeatherModelMapper.convertToModel(it) }
                .subscribe(::showCurrentWeather, {}) //NoOps on error. Already handled.
    }

    private fun showForecasts(forecasts: List<ForecastModel>) {
        //ToDo: wrap mutable live data in a class to ensure that
        // the view state is never null. The current implementation
        // of LiveData is gross and should account for this.
        viewstate.value =
                viewstate.value?.copy(forecasts = forecasts, showingProgressSpinner = false)
    }

    private fun showCurrentWeather(currentWeather: CurrentWeatherModel) {
        viewstate.value =
                viewstate.value?.copy(
                        currentWeather = currentWeather,
                        showingProgressSpinner = false)
    }

    private fun showProgressSpinner() {
        viewstate.value = viewstate.value?.copy(showingProgressSpinner = true)
    }

    private fun showErrorDialog(throwable: Throwable) {
        // Reset all data on error and show the error prompt (if it's not already showing)
        val finalViewState = viewstate.value

        finalViewState?.let { if (!finalViewState.showErrorDialog)
            viewstate.value = WeatherListViewState(
                forecasts = emptyList(),
                currentWeather = null,
                showingProgressSpinner = false,
                zipCode = null,
                showErrorDialog = true)}
    }

    class Factory(private val weatherRepository: WeatherRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T
                = WeatherListViewModel(weatherRepository = weatherRepository) as T
    }
}
