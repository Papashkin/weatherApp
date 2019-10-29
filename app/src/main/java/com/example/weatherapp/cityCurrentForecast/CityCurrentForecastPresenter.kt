package com.example.weatherapp.cityCurrentForecast

import com.example.weatherapp.ObjectBox.boxStore
import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.cityCurrentForecast.domain.GetDetails
import com.example.weatherapp.cityCurrentForecast.models.ObservationsModel
import com.example.weatherapp.cityCurrentForecast.models.StationDTO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import io.objectbox.Box
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

    private val observationBox: Box<ObservationsModel> = boxStore.boxFor(ObservationsModel::class.java)
    fun loadDetails() = launch {
        try {
            val response = getDetails()
//            observationBox = boxStore.boxFor(ObservationsModel::class.java)
            observationBox.removeAll()
            observationBox.put(response)
            val observations = response.observations.filter { it.name.contains(name) }
            viewState.show(observations)
        } catch (e: Exception) {
            e.printStackTrace()
            val model = observationBox.all
            val observations = model.firstOrNull()?.observations?.filter { it.name.contains(name) }
            if (!observations.isNullOrEmpty()) {
                viewState.show(observations)
            } else {
                viewState.showErrorToast()
            }
        }

    }
}