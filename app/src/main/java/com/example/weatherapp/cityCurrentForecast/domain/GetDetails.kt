package com.example.weatherapp.cityCurrentForecast.domain

import com.example.weatherapp.WeatherService
import com.example.weatherapp.cityCurrentForecast.models.ObservationsModel
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import javax.inject.Inject

@AutoFactory
class GetDetails @Inject constructor(
    @Provided private val service: WeatherService
) {
    suspend operator fun invoke(): ObservationsModel {
        return service.getPlaceDetails()
    }
}