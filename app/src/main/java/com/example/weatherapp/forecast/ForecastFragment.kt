package com.example.weatherapp.forecast

import android.os.Bundle
import android.view.View
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.di
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ForecastFragment :
    ForecastView,
    BaseFragment(R.layout.fragment_forecast) {

    @Inject lateinit var factory: ForecastPresenterFactory

    private val presenter by moxyPresenter { factory.create() }

    init {
        di.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.showData()
    }

    override fun update() {

    }

    override fun getNexDayForecast() {

    }
}