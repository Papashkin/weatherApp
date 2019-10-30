package com.example.weatherapp.observation.domain

import com.example.weatherapp.WeatherService
import com.example.weatherapp.observation.models.ObservationsModel
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