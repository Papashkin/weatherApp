package com.example.weatherapp.observation.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "observation")
data class ObservationPOJO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
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
    val uvindex: String,
    val timestamp: String
)