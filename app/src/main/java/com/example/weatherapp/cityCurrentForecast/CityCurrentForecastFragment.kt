package com.example.weatherapp.cityCurrentForecast

import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.di

class CityCurrentForecastFragment:
    CityCurrentForecastView,
    BaseFragment(R.layout.fragment_city_weather_info) {

    init {
        di.inject(this)
    }

    override fun show() {

    }
}