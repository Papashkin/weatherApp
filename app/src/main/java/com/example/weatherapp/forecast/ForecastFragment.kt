package com.example.weatherapp.forecast

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.di
import com.example.weatherapp.forecast.models.ForecastsModel
import kotlinx.android.synthetic.main.fragment_forecast.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ForecastFragment :
    ForecastView,
    BaseFragment(R.layout.fragment_forecast) {

    @Inject lateinit var factory: ForecastPresenterFactory

    private val presenter by moxyPresenter { factory.create() }
    private val adapter: ForecastAdapter = ForecastAdapter {
        presenter.onPlaceClick(it)
    }

    init {
        di.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadData()
    }

    override fun update(forecast: ForecastsModel) {
        adapter.setNewForecast(forecast)
        rvForecasts.adapter = adapter
    }

    override fun showError() {
        showToast(R.string.err_no_data)
    }

    override fun hideLoading() {
        clLoading.visibility = View.GONE
    }

    override fun toCityDetails(name: String) {
        findNavController().navigate(
            ForecastFragmentDirections.actionForecastFragmentToObservationFragment(name))
    }
}