package com.example.weatherapp.observation

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.convertToDTO
import com.example.weatherapp.convertToPOJO
import com.example.weatherapp.observation.data.ObservationRepository
import com.example.weatherapp.observation.domain.GetDetails
import com.example.weatherapp.observation.models.ObservationsModel
import com.example.weatherapp.observation.models.StationDTO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.text.DateFormat
import javax.inject.Inject

interface ObservationView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun show(observations: List<StationDTO>)

    @StateStrategyType(SkipStrategy::class)
    fun setObservationTime(observationTime: String)

    @StateStrategyType(SkipStrategy::class)
    fun hideLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast()
}

@AutoFactory
@InjectViewState
class ObservationPresenter @Inject constructor(
    private val name: String,
    @Provided private val getDetails: GetDetails,
    @Provided private val observationRepository: ObservationRepository
) : BasePresenter<ObservationView>() {

    fun loadDetails() = launch {
        try {
            val response = getDetails()
            saveDataInDB(response).await()
            val observations = response.observations.filter { it.name.contains(name) }
            val timestamp = response.timestamp.toLong()
            viewState.setObservationTime(DateFormat.getTimeInstance().format(timestamp))
            viewState.show(observations)
            viewState.hideLoading()
        } catch (e: Exception) {
            val model: ObservationsModel = getStationsFromDB(name).await()
            val timestamp = model.timestamp.toLong()
            viewState.setObservationTime(DateFormat.getTimeInstance().format(timestamp))
            if (model.observations.isNullOrEmpty()) {
                e.printStackTrace()
                viewState.hideLoading()
                viewState.showErrorToast()
            } else {
                viewState.show(model.observations)
                viewState.hideLoading()
            }
        }
    }

    private fun getStationsFromDB(name: String) = async {
        val timestamp = observationRepository.getTimestamp()
        val observationPojos = observationRepository.getByName(name)
        val stations = observationPojos?.map { it.convertToDTO() }
        ObservationsModel(timestamp, stations ?: listOf())
    }

    private fun saveDataInDB(model: ObservationsModel) = async {
        observationRepository.deleteAll()
        val observationPojos = model.convertToPOJO()
        observationRepository.insert(observationPojos)
    }
}