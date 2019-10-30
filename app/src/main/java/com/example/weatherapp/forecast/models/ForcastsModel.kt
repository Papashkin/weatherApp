package com.example.weatherapp.forecast.models

import androidx.annotation.DrawableRes
import com.example.weatherapp.R
import com.google.gson.annotations.SerializedName

data class ForecastsModel(
    var forecasts: List<ForecastDTO> = listOf()
)

data class ForecastDTO(
    var date: String,
    var day: DayNightDTO? = null,
    var night: DayNightDTO? = null
)

data class DayNightDTO(
    var phenomenon: Phenomenon,
    var tempmin: Double?,
    var tempmax: Double?,
    var text: String,
    var sea: String? = null,
    var peipsi: String? = null,
    var places: List<PlaceDTO>,
    var winds: List<WindDTO>
)

data class WindDTO(
    var name: String,
    var direction: WindDirection,
    var speedmin: Double?,
    var speedmax: Double?
)

data class PlaceDTO(
    var name: String,
    var phenomenon: Phenomenon,
    var tempmin: Double?,
    var tempmax: Double?
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

enum class WindDirection(@DrawableRes val drawableId: Int) {
    @SerializedName("North wind")
    north(R.drawable.wind_north),
    @SerializedName("Northwest wind")
    northwest(R.drawable.wind_northwest),
    @SerializedName("West wind")
    west(R.drawable.wind_west),
    @SerializedName("Southwest wind")
    southwest(R.drawable.wind_southwest),
    @SerializedName("South wind")
    south(R.drawable.wind_south),
    @SerializedName("Southeast wind")
    southeast(R.drawable.wind_southeast),
    @SerializedName("East wind")
    east(R.drawable.wind_east),
    @SerializedName("Northeast wind")
    northeast(R.drawable.wind_northeast)
}