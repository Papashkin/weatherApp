package com.example.weatherapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.forecast.domain.ForecastDao
import com.example.weatherapp.forecast.models.ForecastPojo
import com.example.weatherapp.forecast.models.PlacePojo
import com.example.weatherapp.forecast.models.WindPojo

@Database(
    entities = [
        ForecastPojo::class,
        WindPojo::class,
        PlacePojo::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}