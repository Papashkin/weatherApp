package com.example.weatherapp.cityCurrentForecast.models

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
class ObservationsModel(
    @Id var id: Long,
    val timestamp: String,
    val observations: List<StationDTO> = listOf()
) {
    @Backlink(to = "observationsModel")
    lateinit var observationsMany: ToMany<StationDTO>
}

@Entity
class StationDTO(
    @Id var id: Long,
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
) {
    lateinit var observationsModel: ToOne<ObservationsModel>
}