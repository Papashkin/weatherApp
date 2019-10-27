package com.example.weatherapp.forecast.domain

import com.example.weatherapp.WeatherService
import com.example.weatherapp.forecast.models.ForecastsModel
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import javax.inject.Inject

@AutoFactory
class GetForecasts @Inject constructor(
    @Provided private val service: WeatherService
) {
    suspend operator fun invoke(): ForecastsModel {
        return service.getForecasts()
    }
}