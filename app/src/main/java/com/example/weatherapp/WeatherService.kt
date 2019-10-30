package com.example.weatherapp

import com.example.weatherapp.observation.models.ObservationsModel
import com.example.weatherapp.forecast.models.ForecastsModel
import retrofit2.http.GET

interface WeatherService {

    @GET("forecast")
    suspend fun getForecasts(): ForecastsModel

    @GET("current")
    suspend fun getPlaceDetails(): ObservationsModel
}