package com.example.weatherapp.observation

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.di
import com.example.weatherapp.observation.models.StationDTO
import kotlinx.android.synthetic.main.fragment_observation.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ObservationFragment :
    ObservationView,
    BaseFragment(R.layout.fragment_observation) {

    @Inject
    lateinit var factory: ObservationPresenterFactory

    private val args: ObservationFragmentArgs by navArgs()

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().navigateUp()
        }
    }

    private val presenter: ObservationPresenter by moxyPresenter { factory.create(args.name) }

    private val adapter: ObservationsAdapter = ObservationsAdapter()

    init {
        di.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
        tvCityName.text = getString(R.string.observation_title, args.name)
        presenter.loadDetails()
    }

    override fun show(observations: List<StationDTO>) {
        rvObservations.adapter = adapter
        adapter.setNewStations(observations)
    }

    override fun setObservationTime(observationTime: String) {
        tvTimeStamp.text = observationTime
    }

    override fun showErrorToast() {
        showToast(R.string.err_no_data)
    }

    override fun hideLoading() {
        clLoading.visibility = View.GONE
    }
}