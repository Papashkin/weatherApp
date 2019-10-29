package com.example.weatherapp.forecast.models

import androidx.annotation.DrawableRes
import com.example.weatherapp.R
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.*
import io.objectbox.converter.PropertyConverter
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
data class ForecastsModel(
    @Id var id: Long,
    var forecasts: List<ForecastDTO> = listOf()
) {
    constructor(): this(0, listOf())

    @Backlink(to = "forecastsModel")
    lateinit var forecastsMany: ToMany<ForecastDTO>
}

@Entity
data class ForecastDTO(
    @Id var id: Long,
    val date: String,
    @Transient val day: DayNightDTO,
    @Transient val night: DayNightDTO
) {
    lateinit var forecastsModel: ToOne<ForecastsModel>
}

@Entity
data class DayNightDTO(
    @Id var id: Long,

    @Convert(converter = PhenomenonConverter::class, dbType = String::class)
    val phenomenon: Phenomenon,

    val tempmin: Double = 0.0,
    val tempmax: Double = 0.0,
    val text: String,
    val sea: String? = null,
    val peipsi: String? = null,
    val places: List<PlaceDTO>,
    val winds: List<WindDTO>
) {
    lateinit var forecastDTO: ToOne<ForecastDTO>

    @Backlink(to = "dayNightDTO")
    lateinit var placesMany: ToMany<PlaceDTO>

    @Backlink(to = "dayNightDTO")
    lateinit var windsMany: ToMany<WindDTO>
}

@Entity
data class WindDTO(
    @Id var id: Long,
    val name: String,
    @Transient val direction: WindDirection,
    val speedmin: Double,
    val speedmax: Double
) {
    lateinit var dayNightDTO: ToOne<DayNightDTO>
}

@Entity
data class PlaceDTO(
    @Id var id: Long,
    val name: String,

    @Convert(converter = PhenomenonConverter::class, dbType = String::class)
    val phenomenon: Phenomenon,

    val tempmin: Double,
    val tempmax: Double
) {
    lateinit var dayNightDTO: ToOne<DayNightDTO>
}

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

class PhenomenonConverter : PropertyConverter<Phenomenon, String> {

    override fun convertToDatabaseValue(entityProperty: Phenomenon?): String {
        return entityProperty?.name ?: ""
    }

    override fun convertToEntityProperty(databaseValue: String?): Phenomenon {
        return Phenomenon.valueOf(databaseValue ?: "")
    }
}