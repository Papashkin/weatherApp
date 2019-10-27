package com.example.weatherapp.forecast.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.forecast.models.ForecastDTO
import com.example.weatherapp.forecast.models.ForecastsModel
import com.example.weatherapp.forecast.models.Phenomenon
import kotlinx.android.synthetic.main.card_forecast.view.*

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private var forecast: ForecastsModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int = forecast?.forecasts?.size ?: 0

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        forecast?.let {
            holder.bind(it.forecasts[position])
        }
    }

    fun setNewForecast(item: ForecastsModel) {
        forecast = null
        forecast = item
        notifyDataSetChanged()
    }
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ForecastDTO) {
        itemView.tvDate.text = item.date
        with(item.day) {
            itemView.tvDayDesc.text = this.text
            if (this.sea.isNullOrBlank()) {
                itemView.tvDaySea.visibility = View.GONE
            } else {
                itemView.tvDaySea.visibility = View.VISIBLE
                itemView.tvDaySea.text = this.sea
            }
            if (this.peipsi.isNullOrBlank()) {
                itemView.tvDayPeipsi.visibility = View.GONE
            } else {
                itemView.tvDayPeipsi.visibility = View.VISIBLE
                itemView.tvDayPeipsi.text = this.peipsi
            }
            itemView.tvDayTemp.text = setTempMinMax(this.tempmin, this.tempmax)
            itemView.ivDayPhenomenon.setImageDrawable(getImageDrawable(this.phenomenon))
        }
        with(item.night) {
            itemView.tvNightDesc.text = this.text
            if (this.sea.isNullOrBlank()) {
                itemView.tvNightSea.visibility = View.GONE
            } else {
                itemView.tvNightSea.visibility = View.VISIBLE
                itemView.tvNightSea.text = this.sea
            }
            if (this.peipsi.isNullOrBlank()) {
                itemView.tvNightPeipsi.visibility = View.GONE
            } else {
                itemView.tvNightPeipsi.visibility = View.VISIBLE
                itemView.tvNightPeipsi.text = this.peipsi
            }
            itemView.tvNightTemp.text = setTempMinMax(this.tempmin, this.tempmax)
            itemView.ivNightPhenomenon.setImageDrawable(getImageDrawable(this.phenomenon))
        }

        itemView.switchDayNight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                itemView.clDay.visibility = View.VISIBLE
                itemView.clNight.visibility = View.GONE
            } else {
                itemView.clDay.visibility = View.GONE
                itemView.clNight.visibility = View.VISIBLE
            }
        }

    }

    private fun getImageDrawable(phenomenon: Phenomenon): Drawable? {
        val drawableId = when (phenomenon) {
            Phenomenon.cloudy, Phenomenon.fewClouds -> R.drawable.phenomenon_cloud
            Phenomenon.cloudyWithClearSpells -> R.drawable.phenomenon_cloud_with_spells
            Phenomenon.fog, Phenomenon.mist -> R.drawable.phenomenon_fog
            Phenomenon.lightRain, Phenomenon.moderateRain, Phenomenon.heavyRain -> R.drawable.phenomenon_rain
            Phenomenon.lightShower, Phenomenon.moderateShower, Phenomenon.heavyShower -> R.drawable.phenomenon_shower
            Phenomenon.lightSleet, Phenomenon.moderateSleet -> R.drawable.phenomenon_sleet
            Phenomenon.lightSnowShower, Phenomenon.moderateSnowShower, Phenomenon.heavySnowShower -> R.drawable.phenomenon_snow_shower
            Phenomenon.lightSnowfall, Phenomenon.moderateSnowfall -> R.drawable.phenomenon_snow
            else -> null
        }
        return if (drawableId != null) ContextCompat.getDrawable(
            itemView.context,
            drawableId
        ) else null
    }

    private fun setTempMinMax(tempMin: Double?, tempMax: Double?): String? {
        val correctTempMin =
            if ((tempMin?.toInt() ?: 0) > 0) "+${tempMin?.toInt() ?: 0}" else "${tempMin?.toInt()
                ?: 0}"
        val correctTempMax =
            if ((tempMax?.toInt() ?: 0) > 0) "+${tempMax?.toInt() ?: 0}" else "${tempMax?.toInt()
                ?: 0}"

        return "$correctTempMin .. $correctTempMax"
    }
}