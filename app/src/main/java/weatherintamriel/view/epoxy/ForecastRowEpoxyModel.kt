package weatherintamriel.view.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.squareup.picasso.Picasso
import justiceadams.com.weatherintamriel.R
import weatherintamriel.model.ForecastModel

data class ForecastRowEpoxyModel(private val forecast: ForecastModel) : EpoxyModelWithHolder<ViewHolder>() {
    override fun getDefaultLayout() = R.layout.row_regular_forecast_item

    override fun createNewHolder() = ViewHolder()

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        populateViewHolderText(holder)
        loadIcon(holder)
    }

    private fun populateViewHolderText(holder: ViewHolder) {
        holder.dateView.text = forecast.date
        holder.descriptionView.text = forecast.description
        holder.maxTemperatureView.text = forecast.high.toString()
        holder.minTemperatureView.text = forecast.low.toString()
    }

    private fun loadIcon(holder: ViewHolder) {
        Picasso.with(holder.iconView.context).load(forecast.iconUrl).into(holder.iconView)
    }
}

class ViewHolder : EpoxyHolder() {
    lateinit var iconView: ImageView
    lateinit var dateView: TextView
    lateinit var descriptionView: TextView
    lateinit var maxTemperatureView: TextView
    lateinit var minTemperatureView: TextView


    override fun bindView(itemView: View) {
        iconView = itemView.findViewById(R.id.icon)
        dateView = itemView.findViewById(R.id.date)
        descriptionView = itemView.findViewById(R.id.description)
        maxTemperatureView = itemView.findViewById(R.id.maxTemperature)
        minTemperatureView = itemView.findViewById(R.id.minTemperature)
    }
}
