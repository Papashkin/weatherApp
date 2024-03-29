package com.example.weatherapp.forecast.models

import androidx.room.*

@Entity(tableName = "forecast")
data class ForecastPOJO(
    @PrimaryKey var date: String,
    @Embedded(prefix = "day_") var day: DayNightPOJO? = null,
    @Embedded(prefix = "night_") var night: DayNightPOJO? = null
)

@TypeConverters(ForecastConverter::class)
data class DayNightPOJO(
    var date: String,
    var phenomenon: Phenomenon,
    var tempmin: Double?,
    var tempmax: Double?,
    var text: String,
    var sea: String? = null,
    var peipsi: String? = null
)

@Entity(tableName = "wind")
@TypeConverters(ForecastConverter::class)
data class WindPOJO(
    @PrimaryKey(autoGenerate = true) var windId: Long,
    var date: String,
    var forecastId: Long,
    var name: String,
    var direction: WindDirection,
    var speedmin: Double?,
    var speedmax: Double?,
    var isDay: Boolean
)

@Entity(tableName = "place")
@TypeConverters(ForecastConverter::class)
data class PlacePOJO(
    @PrimaryKey(autoGenerate = true) var placeId: Long,
    var date: String,
    var forecastId: Long,
    var name: String,
    var phenomenon: Phenomenon,
    var tempmin: Double?,
    var tempmax: Double?,
    var isDay: Boolean
)

class ForecastConverter {

    @TypeConverter
    fun fromPhenomenon(phenomenon: Phenomenon): String = phenomenon.name

    @TypeConverter
    fun toPhenomenon(data: String): Phenomenon = Phenomenon.valueOf(data)

    @TypeConverter
    fun fromWindDirection(windDirection: WindDirection): String = windDirection.name

    @TypeConverter
    fun toWindDirection(data: String): WindDirection = WindDirection.valueOf(data)

}