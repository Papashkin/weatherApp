package com.example.weatherapp.base

import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<TView: MvpView>: MvpPresenter<TView>() {
}