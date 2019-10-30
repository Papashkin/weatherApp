package com.example.weatherapp.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes layoutId: Int) : MvpAppCompatFragment(layoutId) {

    fun showToast(@StringRes messageId: Int) {
        Toast.makeText(this.requireContext(), messageId, Toast.LENGTH_SHORT).show()
    }
}