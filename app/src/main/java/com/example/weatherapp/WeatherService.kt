package com.example.weatherapp

import com.example.weatherapp.forecast.models.ForecastsModel
import com.example.weatherapp.cityCurrentForecast.models.ObservationsModel
import retrofit2.http.GET

interface WeatherService {

    @GET("/estonia/forecast")
    suspend fun getForecasts(): ForecastsModel

    @GET("/estonia/current")
    suspend fun getPlaceDetails(): ObservationsModel
}