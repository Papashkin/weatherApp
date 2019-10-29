package com.example.weatherapp.cityCurrentForecast

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.cityCurrentForecast.domain.GetDetails
import com.example.weatherapp.cityCurrentForecast.models.StationDTO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

interface CityCurrentForecastView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun show(observations: List<StationDTO>)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast()
}

@AutoFactory
@InjectViewState
class CityCurrentForecastPresenter @Inject constructor(
    private val name: String,
    @Provided private val getDetails: GetDetails
) : BasePresenter<CityCurrentForecastView>() {

    fun loadDetails() = launch {
        try {
            val response = getDetails()
            val observations = response.observations.filter { it.name.contains(name) }
            viewState.show(observations)
        } catch (e: Exception) {
            e.printStackTrace()
            viewState.showErrorToast()
        }

    }
}