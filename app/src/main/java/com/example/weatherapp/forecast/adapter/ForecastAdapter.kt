package com.example.weatherapp.forecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.forecast.models.ForecastDTO
import com.example.weatherapp.forecast.models.ForecastsModel
import kotlinx.android.synthetic.main.card_forecast.view.*

class ForecastAdapter: RecyclerView.Adapter<ForecastViewHolder>() {
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
            itemView.tvDayTemp.text = ("${this.tempmin ?: 0} .. ${this.tempmax ?: 0}")
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
            itemView.tvNightTemp.text = ("${this.tempmin ?: 0} .. ${this.tempmax ?: 0}")
        }

    }
}