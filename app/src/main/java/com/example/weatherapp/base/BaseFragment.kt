package com.example.weatherapp.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes layoutId: Int) : MvpAppCompatFragment(layoutId) {

    fun showToast(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}