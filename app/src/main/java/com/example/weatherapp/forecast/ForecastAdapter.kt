package com.example.weatherapp.forecast

import android.annotation.SuppressLint
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.forecast.models.ForecastDTO
import com.example.weatherapp.forecast.models.ForecastsModel
import kotlinx.android.synthetic.main.card_forecast.view.*
import kotlin.math.roundToInt

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private var forecast: ForecastsModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int = forecast?.forecasts?.size ?: 0

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        forecast?.let { holder.bind(it.forecasts[position]) }
    }

    fun setNewForecast(item: ForecastsModel) {
        forecast = null
        forecast = item
        notifyDataSetChanged()
    }
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /* this variable allows to scroll text inside of the Sea and Peipsi text views,
        independently of the recycler view behaviour */
    private val listener = View.OnTouchListener { v, event ->
        val isLarger: Boolean = (v as TextView).lineCount * v.lineHeight > v.getHeight()
        if (event.action == MotionEvent.ACTION_MOVE && isLarger) {
            v.getParent().requestDisallowInterceptTouchEvent(true)
        } else {
            v.getParent().requestDisallowInterceptTouchEvent(false)
        }
        false
    }

    fun bind(item: ForecastDTO) {
        itemView.tvDate.text = item.date
        with(item.day) {
            itemView.tvDayDesc.text = this.text
            if (this.sea.isNullOrBlank()) {
                itemView.llDaySea.visibility = View.GONE
            } else {
                itemView.llDaySea.visibility = View.VISIBLE
                itemView.tvDaySea.text = this.sea
            }
            if (this.peipsi.isNullOrBlank()) {
                itemView.llDayPeipsi.visibility = View.GONE
            } else {
                itemView.llDayPeipsi.visibility = View.VISIBLE
                itemView.tvDayPeipsi.text = this.peipsi
            }
            itemView.tvDayTemp.text =
                setTempMinMax(this.tempmin.roundToInt(), this.tempmax.roundToInt())
            itemView.ivDayPhenomenon.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, this.phenomenon.drawableId)
            )
            setMovementMethodAndTouchListener(itemView.tvDaySea)
            setMovementMethodAndTouchListener(itemView.tvDayPeipsi)
        }
        with(item.night) {
            itemView.tvNightDesc.text = this.text
            if (this.sea.isNullOrBlank()) {
                itemView.llNightSea.visibility = View.GONE
            } else {
                itemView.llNightSea.visibility = View.VISIBLE
                itemView.tvNightSea.text = this.sea
            }
            if (this.peipsi.isNullOrBlank()) {
                itemView.llNightPeipsi.visibility = View.GONE
            } else {
                itemView.llNightPeipsi.visibility = View.VISIBLE
                itemView.tvNightPeipsi.text = this.peipsi
            }
            itemView.tvNightTemp.text =
                setTempMinMax(this.tempmin.roundToInt(), this.tempmax.roundToInt())
            itemView.ivNightPhenomenon.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, this.phenomenon.drawableId)
            )
            setMovementMethodAndTouchListener(itemView.tvNightSea)
            setMovementMethodAndTouchListener(itemView.tvNightPeipsi)
        }
        itemView.btnShowNightWeather.setOnClickListener { showNightAndHideDay() }
        itemView.btnShowDayWeather.setOnClickListener { showDayAndHideNight() }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setMovementMethodAndTouchListener(view: TextView) {
        view.movementMethod = ScrollingMovementMethod()
        view.setOnTouchListener(listener)
    }

    private fun setTempMinMax(tempMin: Int, tempMax: Int): String {
        val correctTempMin = if (tempMin > 0) "+$tempMin" else "$tempMin"
        val correctTempMax = if (tempMax > 0) "+$tempMax" else "$tempMax"

        return "$correctTempMin .. $correctTempMax"
    }

    private fun showDayAndHideNight() {
        itemView.clDay.visibility = View.VISIBLE
        itemView.clNight.visibility = View.GONE
        itemView.invalidate()
    }

    private fun showNightAndHideDay() {
        itemView.clDay.visibility = View.GONE
        itemView.clNight.visibility = View.VISIBLE
        itemView.invalidate()
    }
}