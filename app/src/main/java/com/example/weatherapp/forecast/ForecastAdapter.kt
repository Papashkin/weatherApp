package com.example.weatherapp.forecast

import android.annotation.SuppressLint
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.forecast.models.*
import kotlinx.android.synthetic.main.card_forecast.view.*
import kotlinx.android.synthetic.main.card_forecast_place.view.*
import kotlinx.android.synthetic.main.card_forecast_wind.view.*
import kotlin.math.roundToInt

class ForecastAdapter(
    private val onPlaceClick: (name: String) -> Unit
) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
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

    inner class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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
            setNewData(item.day)
            setMovementMethodAndTouchListener(itemView.tvSea)
            setMovementMethodAndTouchListener(itemView.tvPeipsi)

            itemView.btnShowNightWeather.setOnClickListener {
                setNewData(item.night)
                showNightAndHideDay()
            }
            itemView.btnShowDayWeather.setOnClickListener {
                setNewData(item.day)
                showDayAndHideNight()
            }
        }

        private fun setNewData(dayNight: DayNightDTO?) {
            if (dayNight != null) {
                setForecastData(dayNight)
                itemView.invalidate()
            }
        }

        private fun setForecastData(data: DayNightDTO) {
            itemView.tvDesc.text = data.text
            if (data.sea.isNullOrBlank()) {
                itemView.llSea.visibility = View.GONE
            } else {
                itemView.llSea.visibility = View.VISIBLE
                itemView.tvSea.text = data.sea
            }
            if (data.peipsi.isNullOrBlank()) {
                itemView.llPeipsi.visibility = View.GONE
            } else {
                itemView.llPeipsi.visibility = View.VISIBLE
                itemView.tvPeipsi.text = data.peipsi
            }
            if (data.places.isNullOrEmpty()) hidePlaces() else showPlaces(data.places)
            if (data.winds.isNullOrEmpty()) hideWinds() else showWinds(data.winds)
            itemView.tvForecastTemp.text =
                setTempMinMax(data.tempmin?.roundToInt(), data.tempmax?.roundToInt())
            itemView.ivForecastPhenomenon.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, data.phenomenon.drawableId)
            )
        }

        private fun inflateWindView(winds: List<WindDTO>) {
            itemView.llWinds.removeAllViews()
            winds.forEach { wind ->
                val view = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.card_forecast_wind, itemView.llWinds, false)
                view.layoutParams = ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                view.tvWind.text = wind.name
                view.tvSpeed.text =
                    setSpeedMinMax(wind.speedmin?.roundToInt(), wind.speedmax?.roundToInt())
                view.ivDirection.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, wind.direction.drawableId)
                )
                itemView.llWinds.addView(view)
            }
        }

        private fun inflatePlaceView(places: List<PlaceDTO>) {
            itemView.llPlaces.removeAllViews()
            places.forEach { place ->
                val view = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.card_forecast_place, itemView.llPlaces, false)
                view.layoutParams = ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                view.tvPlace.text = place.name
                view.tvTemp.text =
                    setTempMinMax(place.tempmin?.roundToInt(), place.tempmax?.roundToInt())
                view.ivPhenomenon.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, place.phenomenon.drawableId)
                )
                view.tvPlace.setOnClickListener { onPlaceClick.invoke(place.name) }
                itemView.llPlaces.addView(view)
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        private fun setMovementMethodAndTouchListener(view: TextView) {
            view.movementMethod = ScrollingMovementMethod()
            view.setOnTouchListener(listener)
        }

        private fun setTempMinMax(tempMin: Int?, tempMax: Int?): String {
            val tempMinIsNull = tempMin == null
            val tempMaxIsNull = tempMax == null
            return when {
                (tempMinIsNull && tempMaxIsNull) -> "N/A"
                (!tempMinIsNull && tempMaxIsNull) -> if (tempMin!! > 0) "+$tempMin" else "$tempMin"
                (tempMinIsNull && !tempMaxIsNull) -> if (tempMax!! > 0) "+$tempMax" else "$tempMax"
                (!tempMinIsNull && !tempMaxIsNull) -> {
                    "${if (tempMin!! > 0) "+$tempMin" else "$tempMin"} .. ${if (tempMax!! > 0) "+$tempMax" else "$tempMax"}"
                }
                else -> "N/A"
            }
        }

        private fun setSpeedMinMax(speedMin: Int?, speedMax: Int?): String {
            val tempMinIsNull = speedMin == null
            val tempMaxIsNull = speedMax == null
            return when {
                (tempMinIsNull && tempMaxIsNull) -> "N/A"
                (!tempMinIsNull && tempMaxIsNull) -> "$speedMin m/s"
                (tempMinIsNull && !tempMaxIsNull) -> "$speedMax m/s"
                (!tempMinIsNull && !tempMaxIsNull) -> "$speedMin - $speedMax m/s"
                else -> "N/A"
            }
        }

        private fun showDayAndHideNight() {
            itemView.btnShowNightWeather.visibility = View.VISIBLE
            itemView.btnShowDayWeather.visibility = View.GONE
            itemView.invalidate()
        }

        private fun showNightAndHideDay() {
            itemView.btnShowNightWeather.visibility = View.GONE
            itemView.btnShowDayWeather.visibility = View.VISIBLE
            itemView.invalidate()
        }

        private fun showPlaces(places: List<PlaceDTO>) {
            itemView.tvPlacesTitle.visibility = View.VISIBLE
            itemView.dividerPlaces.visibility = View.VISIBLE
            itemView.llPlaces.visibility = View.VISIBLE
            inflatePlaceView(places)
        }

        private fun hidePlaces() {
            itemView.tvPlacesTitle.visibility = View.GONE
            itemView.dividerPlaces.visibility = View.GONE
            itemView.llPlaces.visibility = View.GONE
        }

        private fun showWinds(winds: List<WindDTO>) {
            itemView.tvWindsTitle.visibility = View.VISIBLE
            itemView.llWinds.visibility = View.VISIBLE
            inflateWindView(winds)
        }

        private fun hideWinds() {
            itemView.tvWindsTitle.visibility = View.GONE
            itemView.llWinds.visibility = View.GONE
        }
    }
}