package com.example.weatherapp

import com.example.weatherapp.forecast.models.ForecastsModel
import com.example.weatherapp.cityCurrentForecast.models.ObservationsModel
import retrofit2.http.GET

interface WeatherService {

    @GET("/api/estonia/forecast")
    suspend fun getForecasts(): ForecastsModel

    @GET("/api/estonia/current")
    suspend fun getPlaceDetails(): ObservationsModel
}