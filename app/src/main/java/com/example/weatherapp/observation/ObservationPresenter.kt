package com.example.weatherapp.observation

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.observation.domain.GetDetails
import com.example.weatherapp.observation.domain.ObservationRepo
import com.example.weatherapp.observation.models.ObservationsModel
import com.example.weatherapp.observation.models.StationDTO
import com.example.weatherapp.convertToDTO
import com.example.weatherapp.convertToPOJO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

interface ObservationView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun show(observations: List<StationDTO>)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast()
}

@AutoFactory
@InjectViewState
class ObservationPresenter @Inject constructor(
    private val name: String,
    @Provided private val getDetails: GetDetails,
    @Provided private val observationRepo: ObservationRepo
) : BasePresenter<ObservationView>() {

    fun loadDetails() = launch {
        try {
            val response = getDetails()
            saveDataInDB(response).await()
            val observations = response.observations.filter { it.name.contains(name) }
            viewState.show(observations)
        } catch (e: Exception) {
            val model: ObservationsModel = getStationsFromDB(name).await()
            if (model.observations.isNullOrEmpty()) {
                e.printStackTrace()
                viewState.showErrorToast()
            } else {
                viewState.show(model.observations)
            }
        }
    }

    private fun getStationsFromDB(name: String) = async {
        val timestamp = observationRepo.getTimestamp()
        val observationPojos = observationRepo.getByName(name)
        val stations = observationPojos?.map { it.convertToDTO() }
        ObservationsModel(timestamp, stations ?: listOf())
    }

    private fun saveDataInDB(model: ObservationsModel) = async {
        observationRepo.deleteAll()
        val observationPojos = model.convertToPOJO()
        observationRepo.insert(observationPojos)
    }
}