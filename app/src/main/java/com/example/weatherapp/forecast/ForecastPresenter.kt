package com.example.weatherapp.forecast

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.forecast.domain.ForecastRepo
import com.example.weatherapp.forecast.domain.GetForecasts
import com.example.weatherapp.forecast.models.*
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

interface ForecastView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun update(forecast: ForecastsModel)

    @StateStrategyType(SkipStrategy::class)
    fun showError()

    @StateStrategyType(SkipStrategy::class)
    fun toCityDetails(name: String)
}

@AutoFactory
@InjectViewState
class ForecastPresenter @Inject constructor(
    @Provided val getForecasts: GetForecasts,
    @Provided val forecastRepo: ForecastRepo
) : BasePresenter<ForecastView>() {

    fun loadData() = launch {
        try {
            val forecastModel = getForecasts()
            saveDataToBD(forecastModel).await()
            viewState.update(forecastModel)
        } catch (e: Exception) {
            e.printStackTrace()
            viewState.showError()
        }
    }

    fun onPlaceClick(name: String) {
        viewState.toCityDetails(name)
    }

    private fun saveDataToBD(model: ForecastsModel) = async {
        val windPojos = mutableListOf<WindPojo>()
        val placePojos = mutableListOf<PlacePojo>()
        model.forecasts.forEach { forecast ->
            val id = forecastRepo.insert(forecast.convertToForecastPojo())
            forecast.day.places.forEach {place ->
//                place.convertToPojo()

            }
        }
    }
}