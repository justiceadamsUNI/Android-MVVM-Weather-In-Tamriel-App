package weatherintamriel.view.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.squareup.picasso.Picasso
import justiceadams.com.weatherintamriel.R
import weatherintamriel.model.ForecastModel

class ForecastRowEpoxyModel(private val forecast: ForecastModel)
    : EpoxyModelWithHolder<ViewHolder>() {

    override fun getDefaultLayout() = R.layout.row_regular_forecast_item

    override fun createNewHolder() = ViewHolder()

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        populateViewHolderText(holder)
        loadIcon(holder)
    }

    private fun populateViewHolderText(holder: ViewHolder) {
        holder.dateView.text = forecast.date
        holder.description.text = forecast.description
        holder.maxTemperature.text = "Max Temp: ${forecast.high} F"
        holder.minTemperature.text = "Min Temp: ${forecast.low} F"
    }

    private fun loadIcon(holder: ViewHolder) {
        Picasso.with(holder.iconView.context).load(forecast.iconUrl).into(holder.iconView)
    }
}

class ViewHolder : EpoxyHolder() {
    lateinit var iconView: ImageView
    lateinit var dateView: TextView
    lateinit var description: TextView
    lateinit var maxTemperature: TextView
    lateinit var minTemperature: TextView


    override fun bindView(itemView: View) {
        iconView = itemView.findViewById(R.id.icon)
        dateView = itemView.findViewById(R.id.date)
        description = itemView.findViewById(R.id.description)
        maxTemperature = itemView.findViewById(R.id.maxTemperature)
        minTemperature = itemView.findViewById(R.id.minTemperature)
    }
}
