package com.example.weatherapp.cityCurrentForecast.models

class ObservationsModel(
    val timestamp: String,
    val observations: List<StationDTO>
)

class StationDTO(
    val name: String,
    val wmocode: String,
    val longitude: String,
    val latitude: String,
    val phenomenon: String,
    val visibility: String,
    val precipitations: String,
    val airpressure: String,
    val relativehumidity: String,
    val airtemperature: String,
    val winddirection: String,
    val windspeed: String,
    val windspeedmax: String,
    val waterlevel: String,
    val waterlevel_eh2000: String,
    val watertemperature: String,
    val uvindex: String
)