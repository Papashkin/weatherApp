package com.example.weatherapp.forecast.models

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
    val tempmin: Double? = null,
    val tempmax: Double? = null,
    val text: String,
    val sea: String? = null,
    val peipsi: String? = null,
    val places: List<PlaceDTO>? = null,
    val winds: List<WindDTO>? = null
)

class NightDTO(
    val phenomenon: Phenomenon,
    val tempmin: Double? = null,
    val tempmax: Double? = null,
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
enum class Phenomenon {
    @SerializedName("Clear")
    clear,
    @SerializedName("Few clouds")
    fewClouds,
    @SerializedName("Variable clouds")
    variableClouds,
    @SerializedName("Cloudy with clear spells")
    cloudyWithClearSpells,
    @SerializedName("Cloudy")
    cloudy,
    @SerializedName("Light snow shower")
    lightSnowShower,
    @SerializedName("Moderate snow shower")
    moderateSnowShower,
    @SerializedName("Heavy snow shower")
    heavySnowShower,
    @SerializedName("Light shower")
    lightShower,
    @SerializedName("Moderate shower")
    moderateShower,
    @SerializedName("Heavy shower")
    heavyShower,
    @SerializedName("Light rain")
    lightRain,
    @SerializedName("Moderate rain")
    moderateRain,
    @SerializedName("Heavy rain")
    heavyRain,
    @SerializedName("Risk of glaze")
    riskOfGlaze,
    @SerializedName("Light sleet")
    lightSleet,
    @SerializedName("Moderate sleet")
    moderateSleet,
    @SerializedName("Light snowfall")
    lightSnowfall,
    @SerializedName("Moderate snowfall")
    moderateSnowfall,
    @SerializedName("Heavy snowfall")
    heavySnowfall,
    @SerializedName("Snowstorm")
    snowstorm,
    @SerializedName("Clear")
    driftingSnow,
    @SerializedName("Hail")
    hail,
    @SerializedName("Mist")
    mist,
    @SerializedName("Fog")
    fog,
    @SerializedName("Thunder")
    thunder,
    @SerializedName("Thunderstorm")
    thunderstorm
}
