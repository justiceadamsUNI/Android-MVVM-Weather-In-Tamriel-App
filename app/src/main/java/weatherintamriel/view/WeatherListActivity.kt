package weatherintamriel.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import justiceadams.com.weatherintamriel.R
import kotlinx.android.synthetic.main.weather_list_content.*
import weatherintamriel.WeatherInTamrielApplication
import weatherintamriel.view.epoxy.WeatherListEpoxyController
import weatherintamriel.viewmodel.WeatherListViewModel
import weatherintamriel.viewmodel.WeatherListViewState
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity() {
    private lateinit var weatherListViewModel: WeatherListViewModel
    @Inject lateinit var weatherListViewModelFactory: WeatherListViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as WeatherInTamrielApplication)
                .getWeatherListViewComponent()
                .inject(this)

        setContentView(R.layout.activity_weather_list)
        setUpToolbar()

        weatherListViewModel =
                ViewModelProviders
                        .of(this, weatherListViewModelFactory)
                        .get(WeatherListViewModel::class.java)

        weatherListViewModel.viewstate.observe(this, Observer(::renderState))
    }

    private fun renderState(weatherListViewState: WeatherListViewState?){
        val controller = WeatherListEpoxyController()
        forecast_list.adapter = controller.adapter

        weatherListViewState
                ?.let { setProgressSpinnerVisible(weatherListViewState.showingProgressSpinner) }
                ?: setProgressSpinnerVisible(false)

        controller.setData(
                weatherListViewState?.forecasts.orEmpty(),
                weatherListViewState?.currentWeather)
    }

    private fun setProgressSpinnerVisible(visible: Boolean) {
        if (!visible) {
            progress_spinner.clearAnimation()
            progress_spinner.visibility = View.GONE
        } else{
            progress_spinner.startAnimation(loadAnimation(this, R.anim.infinite_spin))
            progress_spinner.visibility = View.VISIBLE
        }
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }
}
