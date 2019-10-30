package com.example.weatherapp.observation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.observation.models.StationDTO
import kotlinx.android.synthetic.main.card_observation.view.*

class ObservationsAdapter : RecyclerView.Adapter<ObservationsAdapter.ObservationViewHolder>() {
    private var stations: MutableList<StationDTO> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObservationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_observation, parent, false)
        return ObservationViewHolder(view)
    }

    override fun getItemCount(): Int = stations.size

    override fun onBindViewHolder(holder: ObservationViewHolder, position: Int) {
        holder.bind(stations[position])
    }

    fun setNewStations(items: List<StationDTO>) {
        stations.clear()
        stations.addAll(items)
        notifyDataSetChanged()
    }

    inner class ObservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: StationDTO) {
            itemView.tvStationName.text = ("${item.name} (station code #${item.wmocode})")
            itemView.tvLocation.text =
                ("(${String.format("%.4f", item.latitude.toFloat())}; ${String.format(
                    "%.4f",
                    item.longitude.toFloat()
                )})")
            if (item.phenomenon.isEmpty()) {
                itemView.llPhenomenon.visibility = View.GONE
            } else {
                itemView.llPhenomenon.visibility = View.VISIBLE
                itemView.tvPhenomenon.text = item.phenomenon
            }
            if (item.visibility.isEmpty()) {
                itemView.llVisibility.visibility = View.GONE
            } else {
                itemView.llVisibility.visibility = View.VISIBLE
                itemView.tvVisibility.text = ("${item.visibility} metres")
            }
            if (item.precipitations.isEmpty()) {
                itemView.llPrecipitations.visibility = View.GONE
            } else {
                itemView.llPrecipitations.visibility = View.VISIBLE
                itemView.tvPrecipitations.text = item.precipitations
            }
            if (item.airpressure.isEmpty()) {
                itemView.llAirPressure.visibility = View.GONE
            } else {
                itemView.llAirPressure.visibility = View.VISIBLE
                itemView.tvAirPressure.text = ("${item.airpressure} hPa")
            }
            if (item.relativehumidity.isEmpty()) {
                itemView.llRelativeHumidity.visibility = View.GONE
            } else {
                itemView.llRelativeHumidity.visibility = View.VISIBLE
                itemView.tvRelativeHumidity.text = ("${item.relativehumidity}%")
            }
            itemView.tvRelativeHumidity.text = ("${item.relativehumidity}%")
            if (item.airtemperature.isEmpty()) {
                itemView.llAirTemperature.visibility = View.GONE
            } else {
                itemView.llAirTemperature.visibility = View.VISIBLE
                itemView.tvAirTemperature.text = ("${item.airtemperature}°C")
            }
            if (item.winddirection.isEmpty()) {
                itemView.llWindDirection.visibility = View.GONE
            } else {
                itemView.llWindDirection.visibility = View.VISIBLE
                itemView.tvWindDirection.text =
                    ("${getDirectionFromDegrees(item.winddirection)} (${item.winddirection}°)")
            }
            if (item.windspeed.isEmpty()) {
                itemView.llWindSpeed.visibility = View.GONE
            } else {
                itemView.llWindSpeed.visibility = View.VISIBLE
                itemView.tvWindSpeed.text = setSpeedMinMax(item.windspeed, item.windspeedmax)
            }
            if (item.waterlevel.isEmpty()) {
                itemView.llWaterLevel.visibility = View.GONE
            } else {
                itemView.llWaterLevel.visibility = View.VISIBLE
                itemView.tvWaterLevel.text = if (item.waterlevel_eh2000.isEmpty()) {
                    item.waterlevel
                } else {
                    "${item.waterlevel}(${item.waterlevel_eh2000} in EH2000)"
                }
            }
            if (item.watertemperature.isEmpty()) {
                itemView.llWaterTemperature.visibility = View.GONE
            } else {
                itemView.llWaterTemperature.visibility = View.VISIBLE
                itemView.tvWaterTemperature.text = ("${item.watertemperature}°C")
            }
            if (item.uvindex.isEmpty()) {
                itemView.llUvIndex.visibility = View.GONE
            } else {
                itemView.llUvIndex.visibility = View.VISIBLE
                itemView.tvUvIndex.text = item.uvindex
            }
        }

        private fun getDirectionFromDegrees(degrees: String): String {
            val value = degrees.toInt()
            return when {
                (value in 0 .. 22) -> "N"
                (value in 23 .. 67)-> "NE"
                (value in 68 .. 112) -> "E"
                (value in 113 .. 157) -> "SE"
                (value in 158 .. 202) -> "S"
                (value in 203 .. 247) -> "SW"
                (value in 248 .. 292) -> "W"
                (value in 293 .. 337) -> "NW"
                (value in 338 .. 360) -> "N"
                else -> "NA"
            }
        }

        private fun setSpeedMinMax(speed: String, speedMax: String): String {
            val isSspeedEmpty = speed.isEmpty()
            val isSpeedMaxEmpty = speedMax.isEmpty()
            return when {
                (isSspeedEmpty && isSpeedMaxEmpty) -> "N/A"
                (!isSspeedEmpty && isSpeedMaxEmpty) -> "$speed m/s"
                (isSspeedEmpty && !isSpeedMaxEmpty) -> "$speedMax m/s"
                (!isSspeedEmpty && !isSpeedMaxEmpty) -> "$speed - $speedMax m/s"
                else -> "N/A"
            }
        }
    }
}