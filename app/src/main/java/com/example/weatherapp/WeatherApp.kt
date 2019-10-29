package com.example.weatherapp

import android.app.Application

lateinit var di: WeatherAppComponent

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        di = DaggerWeatherAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}