package com.example.weatherapp.forecast

import com.example.weatherapp.ObjectBox.boxStore
import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.forecast.domain.GetForecasts
import com.example.weatherapp.forecast.models.ForecastsModel
import com.example.weatherapp.forecast.models.ForecastsModel_
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import io.objectbox.Box
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
    @Provided val getForecasts: GetForecasts
) : BasePresenter<ForecastView>() {

//    lateinit var forecastBox: Box<ForecastsModel>
    private val forecastBox: Box<ForecastsModel> = boxStore.boxFor(ForecastsModel::class.java)

    fun loadData() = launch {
        try {
            val forecastModel = getForecasts()
//            forecastBox = boxStore.boxFor(ForecastsModel::class.java)
            forecastBox.removeAll()
            val model = ForecastsModel()
            model.forecasts = forecastModel.forecasts
            forecastBox.put(model)
            viewState.update(forecastModel)
        } catch (e: Exception) {
            e.printStackTrace()
            val forecastModel = forecastBox.query().order(ForecastsModel_.id).build().findFirst()
            if (forecastModel != null) {
                viewState.update(forecastModel)
            } else {
                viewState.showError()
            }
        }
    }

    fun onPlaceClick(name: String) {
        viewState.toCityDetails(name)
    }
}