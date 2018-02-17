package weatherintamriel.view.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.squareup.picasso.Picasso
import justiceadams.com.weatherintamriel.R
import weatherintamriel.model.CurrentWeatherModel

data class CurrentWeatherEpoxyModel(private val weather: CurrentWeatherModel) : EpoxyModelWithHolder<CurrentWeatherViewHolder>() {
    override fun getDefaultLayout() = R.layout.row_current_weather

    override fun createNewHolder() = CurrentWeatherViewHolder()

    override fun bind(holder: CurrentWeatherViewHolder) {
        super.bind(holder)

        populateViewHolderText(holder)
        loadIcon(holder)
    }

    private fun populateViewHolderText(holder: CurrentWeatherViewHolder) {
        holder.date.text = weather.date
        holder.description.text = weather.description
        holder.maxTemperature.text = "Max Temp: ${weather.temp_max}°"
        holder.minTemperature.text = "Min Temp: ${weather.temp_min}°"
        holder.currentTemperature.text = "Current: ${weather.temp}°"
        holder.humidity.text = "Humidity: ${weather.humidity}%"
    }

    private fun loadIcon(holder: CurrentWeatherViewHolder) {
        Picasso.with(holder.icon.context).load(weather.iconUrl).into(holder.icon)
    }
}

class CurrentWeatherViewHolder : EpoxyHolder() {
    lateinit var icon: ImageView
    lateinit var date: TextView
    lateinit var description: TextView
    lateinit var maxTemperature: TextView
    lateinit var minTemperature: TextView
    lateinit var currentTemperature: TextView
    lateinit var humidity: TextView

    override fun bindView(itemView: View) {
        icon = itemView.findViewById(R.id.icon)
        date = itemView.findViewById(R.id.date)
        description = itemView.findViewById(R.id.description)
        maxTemperature = itemView.findViewById(R.id.max_temperature)
        minTemperature = itemView.findViewById(R.id.min_temperature)
        currentTemperature = itemView.findViewById(R.id.current_temp)
        humidity = itemView.findViewById(R.id.humidity)
    }
}