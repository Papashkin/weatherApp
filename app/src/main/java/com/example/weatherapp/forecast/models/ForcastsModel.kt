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
    val tempmin: Double,
    val tempmax: Double,
    val text: String,
    val sea: String,
    val peipsi: String,
    val places: List<PlaceDTO>,
    val winds: List<WindDTO>
)

class NightDTO(
    val phenomenon: String,
    val tempmin: Double,
    val tempmax: Double,
    val text: String,
    val sea: String,
    val peipsi: String,
    val places: List<PlaceDTO>,
    val winds: List<WindDTO>
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
