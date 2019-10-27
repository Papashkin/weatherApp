package com.example.weatherapp.cityCurrentForecast

import com.example.weatherapp.base.BasePresenter
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CityCurrentForecastView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun show()
}

@InjectViewState
class CityCurrentForecastPresenter : BasePresenter<CityCurrentForecastView>() {

}