package com.example.weatherapp

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface WeatherService {

    @GET("/api/estonia/forecast")
    fun getForecast(): Observable<ResponseBody>
}