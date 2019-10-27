package com.example.weatherapp.base

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes layoutId: Int) : MvpAppCompatFragment(layoutId) {
}