package com.example.weatherapp.forecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.forecast.models.ForecastsModel

class ForecastAdapter: RecyclerView.Adapter<ForecastViewHolder>() {
    private val forecasts: MutableList<ForecastsModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecasts[position])
    }
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ForecastsModel) {

    }
}