package com.example.weatherapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.observation.domain.ObservationDao
import com.example.weatherapp.observation.models.ObservationPOJO
import com.example.weatherapp.forecast.domain.ForecastDao
import com.example.weatherapp.forecast.models.ForecastPOJO
import com.example.weatherapp.forecast.models.PlacePOJO
import com.example.weatherapp.forecast.models.WindPOJO

@Database(
    entities = [
        ForecastPOJO::class,
        WindPOJO::class,
        PlacePOJO::class,
        ObservationPOJO::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
    abstract fun observationDao(): ObservationDao
}