package com.example.weatherapp.forecast

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.convertToDTO
import com.example.weatherapp.convertToForecastPojo
import com.example.weatherapp.convertToPojo
import com.example.weatherapp.forecast.data.ForecastRepository
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
    @Provided private val getForecasts: GetForecasts,
    @Provided private val forecastRepository: ForecastRepository
) : BasePresenter<ForecastView>() {

    fun loadData() = launch {
        try {
            val forecastModel = getForecasts()
            saveDataToBD(forecastModel).await()
            viewState.update(forecastModel)
        } catch (e: Exception) {
            val forecastModel = getDataFromBD().await()
            if (forecastModel.forecasts.isNullOrEmpty()) {
                e.printStackTrace()
                viewState.showError()
            } else {
                viewState.update(forecastModel)
            }
        }
    }

    fun onPlaceClick(name: String) {
        viewState.toCityDetails(name)
    }

    private fun saveDataToBD(model: ForecastsModel) = async {
        forecastRepository.deleteAll()
        model.forecasts.forEach { forecast ->
            val forecastId = forecastRepository.insert(forecast.convertToForecastPojo())
            // get day places from DTO to POJO
            forecast.day?.places?.map {
                it.convertToPojo(forecast.date, forecastId, true)
            }?.let { forecastRepository.insertPlace(it) }

            // get day winds from DTO to POJO
            forecast.day?.winds?.map {
                it.convertToPojo(forecast.date, forecastId, true)
            }?.let { forecastRepository.insertWind(it) }

            // get night places from DTO to POJO
            forecast.night?.places?.map {
                it.convertToPojo(forecast.date, forecastId, false)
            }?.let { forecastRepository.insertPlace(it) }

            // get night winds from DTO to POJO
            forecast.night?.winds?.map {
                it.convertToPojo(forecast.date, forecastId, false)
            }?.let { forecastRepository.insertWind(it) }
        }
    }

    private fun getDataFromBD() = async {
        val forecastsDTO = mutableListOf<ForecastDTO>()
        val forecastPojos = forecastRepository.getAll()
        forecastPojos.forEach { forecastPojo ->
            val placesPojos = forecastRepository.getForecastPlaces(forecastPojo.date)
            val windsPojos = forecastRepository.getForecastWinds(forecastPojo.date)

            val dayDTO = forecastPojo.day?.convertToDTO(
                places = placesPojos.filter { it.isDay }.map { it.convertToDTO() },
                winds = windsPojos.filter { it.isDay }.map { it.convertToDTO() }
            )

            val nightDTO = forecastPojo.night?.convertToDTO(
                places = placesPojos.filter { !it.isDay }.map { it.convertToDTO() },
                winds = windsPojos.filter { !it.isDay }.map { it.convertToDTO() }
            )

            val forecastDTO = forecastPojo.convertToDTO(day = dayDTO, night = nightDTO)
            forecastsDTO.add(forecastDTO)
        }
        ForecastsModel(forecastsDTO)
    }
}