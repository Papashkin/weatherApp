package com.example.weatherapp.forecast

import com.example.weatherapp.base.BasePresenter
import com.google.auto.factory.AutoFactory
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

interface ForecastView: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun update()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getNexDayForecast()
}

@AutoFactory
@InjectViewState
class ForecastPresenter @Inject constructor() : BasePresenter<ForecastView>() {

    fun showData() {

    }

}