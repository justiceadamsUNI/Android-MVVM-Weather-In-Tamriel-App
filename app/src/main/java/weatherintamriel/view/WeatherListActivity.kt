package weatherintamriel.view

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Typeface
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import justiceadams.com.weatherintamriel.R
import kotlinx.android.synthetic.main.weather_list_content.*
import weatherintamriel.WeatherInTamrielApplication
import weatherintamriel.util.onTextChanged
import weatherintamriel.util.readZipCodeFromSharedPreferences
import weatherintamriel.util.writeZipCodeIntoSharedPreferences
import weatherintamriel.view.dialog.ZipCodeEntryDialogBuilder
import weatherintamriel.view.epoxy.WeatherListEpoxyController
import weatherintamriel.viewmodel.WeatherListViewModel
import weatherintamriel.viewmodel.WeatherListViewState
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity() {
    private var updateZipCodeMenuItemEnabled: Boolean = false
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

        weatherListViewModel.setOnZipcodeSuccessfullyUpdatedListener {
            writeZipCodeIntoSharedPreferences(it)
        }

        weatherListViewModel.viewstate.observe(this, Observer(::renderState))
    }

    private fun renderState(weatherListViewState: WeatherListViewState?) {
        val controller = WeatherListEpoxyController()
        forecast_list.adapter = controller.adapter

        weatherListViewState?.let {
            if(weatherListViewState.zipCode == null) {
                if (weatherListViewState.initialState) determineAndShowStartupState()
                else showZipCodeEntryDialog(
                        if (weatherListViewState.showErrorDialog)
                            R.string.error_finding_zip_code_prompt else
                            R.string.enter_zip_code_prompt)
            }
        }

        weatherListViewState
                ?.let { setProgressSpinnerVisible(weatherListViewState.showingProgressSpinner) }
                ?: setProgressSpinnerVisible(false)

        weatherListViewState
                ?.let { setWeatherDataVisible(!weatherListViewState.showingProgressSpinner) }
                ?: setWeatherDataVisible(false)

        weatherListViewState?.let {
            showZipCodeMenuItemEnabled(!weatherListViewState.showingProgressSpinner)
        }

        weatherListViewState
                ?.let { weatherListViewState.locationInfo?.let{ showToastWithLocationInfo(it)} }

        controller.setData(
                weatherListViewState?.forecasts.orEmpty(),
                weatherListViewState?.currentWeather)
    }

    private fun setProgressSpinnerVisible(visible: Boolean) {
        if (!visible) {
            progress_spinner.clearAnimation()
            progress_spinner.visibility = View.GONE
        } else {
            progress_spinner.startAnimation(loadAnimation(this, R.anim.infinite_spin))
            progress_spinner.visibility = View.VISIBLE
        }
    }

    private fun setWeatherDataVisible(visible: Boolean) {
        if (!visible) {
            forecast_list.visibility = View.GONE
        } else {
            forecast_list.visibility = View.VISIBLE
        }
    }

    private fun determineAndShowStartupState() {
        val zipCode = readZipCodeFromSharedPreferences()

        zipCode?.let { weatherListViewModel.updateZipCode(zipCode) }
                ?: showZipCodeEntryDialog(R.string.enter_zip_code_prompt)
    }

    private fun showToastWithLocationInfo(locationInfo: String) {
        Toast.makeText(this, "Showing weather for $locationInfo", Toast.LENGTH_SHORT)
                .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.set_zip_code -> {
            showZipCodeEntryDialog(R.string.enter_zip_code_prompt)
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun showZipCodeMenuItemEnabled(enableUpdateZipCodeItem: Boolean) {
        updateZipCodeMenuItemEnabled = enableUpdateZipCodeItem
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(0)?.isEnabled = updateZipCodeMenuItemEnabled
        return true
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun showZipCodeEntryDialog(@StringRes dialogMessage: Int): Boolean {
        val dialog = ZipCodeEntryDialogBuilder(this)
                .createAlertDialogWithActionOnZipCodeEntry(weatherListViewModel::updateZipCode)

        dialog.show()
        val positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE)
        val negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE)

        positiveButton.isEnabled = false
        dialog.findViewById<EditText>(R.id.zip_code)?.onTextChanged { zipCode ->
                    positiveButton.isEnabled = !zipCode.isEmpty()
                }

        dialog.findViewById<TextView>(R.id.dialog_message)?.text = getString(dialogMessage)

        val typeface = Typeface.createFromAsset(assets, "fonts/Planewalker.otf")
        positiveButton.typeface = typeface
        negativeButton.typeface = typeface
        return true
    }
}
