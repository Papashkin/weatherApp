package com.example.weatherapp.forecast

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.forecast.domain.GetForecasts
import com.example.weatherapp.forecast.models.ForecastsModel
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

interface ForecastView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun update(forecast: ForecastsModel)

    @StateStrategyType(SkipStrategy::class)
    fun showError()
}

@AutoFactory
@InjectViewState
class ForecastPresenter @Inject constructor(
    @Provided val getForecasts: GetForecasts
) : BasePresenter<ForecastView>() {

    fun loadData() = launch {
        try {
            val forecasts = getForecasts()
            viewState.update(forecasts)
        } catch (e: Exception) {
            e.printStackTrace()
            viewState.showError()
        }
    }

}