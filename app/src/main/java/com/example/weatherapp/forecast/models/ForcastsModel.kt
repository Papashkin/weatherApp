package com.example.weatherapp.forecast.models

class ForecastsModel(
    val forecasts: List<ForecastDTO>
)

class ForecastDTO(
    val date: String,
    val day: DayDTO,
    val night: NightDTO
)

class DayDTO(
    val phenomenon: String,
    val tempmin: Double? = null,
    val tempmax: Double? = null,
    val text: String,
    val sea: String? = null,
    val peipsi: String? = null,
    val places: List<PlaceDTO>? = null,
    val winds: List<WindDTO>? = null
)

class NightDTO(
    val phenomenon: String,
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
    val direction : String,
    val speedmin: Double,
    val speedmax: Double
)

class PlaceDTO(
    val name	: String,
    val phenomenon: String,
    val tempmin	: Double,
    val tempmax	: Double
)
