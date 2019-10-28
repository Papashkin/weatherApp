package com.example.weatherapp.forecast.models

import androidx.annotation.DrawableRes
import com.example.weatherapp.R
import com.google.gson.annotations.SerializedName

class ForecastsModel(
    val forecasts: List<ForecastDTO>
)

class ForecastDTO(
    val date: String,
    val day: DayDTO,
    val night: NightDTO
)

class DayDTO(
    val phenomenon: Phenomenon,
    val tempmin: Double = 0.0,
    val tempmax: Double = 0.0,
    val text: String,
    val sea: String? = null,
    val peipsi: String? = null,
    val places: List<PlaceDTO>? = null,
    val winds: List<WindDTO>? = null
)

class NightDTO(
    val phenomenon: Phenomenon,
    val tempmin: Double = 0.0,
    val tempmax: Double = 0.0,
    val text: String,
    val sea: String? = null,
    val peipsi: String? = null,
    val places: List<PlaceDTO>? = null,
    val winds: List<WindDTO>? = null
)

class WindDTO(
    val name: String,
    val direction: String,
    val speedmin: Double,
    val speedmax: Double
)

class PlaceDTO(
    val name: String,
    val phenomenon: String,
    val tempmin: Double,
    val tempmax: Double
)

/**
 * Phenomenons:
 */
enum class Phenomenon(@DrawableRes val drawableId: Int) {
    @SerializedName("Clear")
    clear(R.drawable.phenomenon_sun),
    @SerializedName("Few clouds")
    fewClouds(R.drawable.phenomenon_cloud),
    @SerializedName("Variable clouds")
    variableClouds(R.drawable.phenomenon_cloud),
    @SerializedName("Cloudy with clear spells")
    cloudyWithClearSpells(R.drawable.phenomenon_cloud_with_spells),
    @SerializedName("Cloudy")
    cloudy(R.drawable.phenomenon_cloud),
    @SerializedName("Light snow shower")
    lightSnowShower(R.drawable.phenomenon_snow_shower),
    @SerializedName("Moderate snow shower")
    moderateSnowShower(R.drawable.phenomenon_snow_shower),
    @SerializedName("Heavy snow shower")
    heavySnowShower(R.drawable.phenomenon_snow_shower),
    @SerializedName("Light shower")
    lightShower(R.drawable.phenomenon_shower),
    @SerializedName("Moderate shower")
    moderateShower(R.drawable.phenomenon_shower),
    @SerializedName("Heavy shower")
    heavyShower(R.drawable.phenomenon_shower),
    @SerializedName("Light rain")
    lightRain(R.drawable.phenomenon_rain),
    @SerializedName("Moderate rain")
    moderateRain(R.drawable.phenomenon_rain),
    @SerializedName("Heavy rain")
    heavyRain(R.drawable.phenomenon_heavy_rain),
    @SerializedName("Risk of glaze")
    riskOfGlaze(R.drawable.phenomenon_glaze),
    @SerializedName("Light sleet")
    lightSleet(R.drawable.phenomenon_sleet),
    @SerializedName("Moderate sleet")
    moderateSleet(R.drawable.phenomenon_sleet),
    @SerializedName("Light snowfall")
    lightSnowfall(R.drawable.phenomenon_snow),
    @SerializedName("Moderate snowfall")
    moderateSnowfall(R.drawable.phenomenon_snow),
    @SerializedName("Heavy snowfall")
    heavySnowfall(R.drawable.phenomenon_heavy_snow),
    @SerializedName("Snowstorm")
    snowstorm(R.drawable.phenomenon_heavy_snow),
    @SerializedName("Clear")
    driftingSnow(R.drawable.phenomenon_snow),
    @SerializedName("Hail")
    hail(R.drawable.phenomenon_hail),
    @SerializedName("Mist")
    mist(R.drawable.phenomenon_fog),
    @SerializedName("Fog")
    fog(R.drawable.phenomenon_fog),
    @SerializedName("Thunder")
    thunder(R.drawable.phenomenon_thunder),
    @SerializedName("Thunderstorm")
    thunderstorm(R.drawable.phenomenon_thunder)
}
