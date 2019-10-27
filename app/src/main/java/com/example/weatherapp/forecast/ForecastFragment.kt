package com.example.weatherapp.forecast

import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.di
import javax.inject.Inject

class ForecastFragment : ForecastView, BaseFragment(R.layout.fragment_forecast) {


    init {
        di.inject(this)
    }

    override fun update() {

    }

    override fun getNexDayForecast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}