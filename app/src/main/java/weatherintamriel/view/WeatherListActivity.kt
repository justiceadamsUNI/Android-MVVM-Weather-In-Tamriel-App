package weatherintamriel.view

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.set_zip_code -> {
            showZipCodeEntryDialog()
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun showZipCodeEntryDialog(): Boolean {
        val dialog = AlertDialog.Builder(this)
                .setView(R.layout.dialog_set_zip_code)
                .setPositiveButton("Done", { dialog, _ ->
                    // ToDo: handle this case.
                })
                .setNegativeButton("Cancel", { dialog, _ ->
                    dialog.dismiss()
                })
                .create()

        dialog.show()

        val typeface = Typeface.createFromAsset(assets, "fonts/Planewalker.otf")
        dialog.getButton(Dialog.BUTTON_POSITIVE).typeface = typeface
        dialog.getButton(Dialog.BUTTON_NEGATIVE).typeface = typeface
        return true
    }
}
