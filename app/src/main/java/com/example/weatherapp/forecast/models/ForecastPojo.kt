package com.example.weatherapp.forecast.models

import androidx.room.*

@Entity(tableName = "forecast")
data class ForecastPojo(
    @PrimaryKey var date: String,
    @Embedded(prefix = "day_") var day: DayNightPojo,
    @Embedded(prefix = "night_") var night: DayNightPojo
)

@TypeConverters(ForecastConverter::class)
data class DayNightPojo(
    var id: Long,
    var date: String, // the same as in ForecastPojo
    var phenomenon: Phenomenon,
    var tempmin: Double = 0.0,
    var tempmax: Double = 0.0,
    var text: String,
    var sea: String? = null,
    var peipsi: String? = null
)

//@TypeConverters(ForecastConverter::class)
//data class DayPojo(
//    var id: Long = 0,
//    var phenomenon: Phenomenon = Phenomenon.clear,
//    var tempmin: Double = 0.0,
//    var tempmax: Double = 0.0,
//    var text: String = "",
//    var sea: String? = null,
//    var peipsi: String? = null
//    @Relation(entity = PlacePojo::class, entityColumn = "placeId", parentColumn = "day_dayId")
//    var places: List<PlacePojo> = listOf(),
//    @Relation(entity = WindPojo::class, entityColumn = "windId", parentColumn = "day_dayId")
//    var winds: List<WindPojo> = listOf()
//)

//@TypeConverters(ForecastConverter::class)
//data class NightPojo(
//    var id: Long,
//    var phenomenon: Phenomenon,
//    var tempmin: Double = 0.0,
//    var tempmax: Double = 0.0,
//    var text: String,
//    var sea: String? = null,
//    var peipsi: String? = null
//    @Relation(entity = PlacePojo::class, entityColumn = "placeId", parentColumn = "night_nightId")
//    var places: List<PlacePojo>,
//    @Relation(entity = WindPojo::class, entityColumn = "windId", parentColumn = "night_nightId")
//    var winds: List<WindPojo>
//)

@Entity(tableName = "wind")
@TypeConverters(ForecastConverter::class)
data class WindPojo(
    @PrimaryKey(autoGenerate = true) var windId: Long,
    var date: String, // the same as in ForecastPojo
    var dayNightId: Long,
    var name: String,
    var direction: WindDirection,
    var speedmin: Double,
    var speedmax: Double
)

@Entity(tableName = "place")
@TypeConverters(ForecastConverter::class)
data class PlacePojo(
    @PrimaryKey(autoGenerate = true) var placeId: Long,
    var date: String, // the same as in ForecastPojo
    var dayNightId: Long,
    var name: String,
    var phenomenon: Phenomenon,
    var tempmin: Double,
    var tempmax: Double
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