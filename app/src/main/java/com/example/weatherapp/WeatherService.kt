package com.example.weatherapp

import okhttp3.ResponseBody
import retrofit2.http.GET

interface WeatherService {

    @GET("/api/estonia/forecast")
    suspend fun getEstForecast(): ResponseBody

    @GET("/api/estonia/current")
    suspend fun getEstCurrent(): ResponseBody

    @GET("/api/world/current")
    suspend fun getWorldCurrent(): ResponseBody

    @GET("/api/world/locations")
    suspend fun getWorldLocations(): ResponseBody

    @GET("/api/world/locations/{id}")
    suspend fun getWorldLocationById(): ResponseBody

    @GET("/api/world/dates")
    suspend fun getWorldDates(): ResponseBody
}