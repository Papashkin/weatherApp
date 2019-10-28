package com.example.weatherapp.cityCurrentForecast

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.cityCurrentForecast.models.StationDTO
import com.example.weatherapp.di
import kotlinx.android.synthetic.main.fragment_city_weather_info.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CityCurrentForecastFragment :
    CityCurrentForecastView,
    BaseFragment(R.layout.fragment_city_weather_info) {

    @Inject
    lateinit var factory: CityCurrentForecastPresenterFactory

    private val args: CityCurrentForecastFragmentArgs by navArgs()

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().navigateUp()
        }
    }

    private val presenter: CityCurrentForecastPresenter by moxyPresenter { factory.create(args.name) }

    private val adapter: ObservationsAdapter = ObservationsAdapter()

    init {
        di.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
        tvCityName.text = args.name
        presenter.loadDetails()
    }

    override fun show(observations: List<StationDTO>) {
        rvObservations.adapter = adapter
        adapter.setNewStations(observations)
    }

    override fun showErrorToast() {
        showToast("No data to show loading from internet")
    }
}