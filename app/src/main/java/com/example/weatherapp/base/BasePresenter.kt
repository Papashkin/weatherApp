package com.example.weatherapp.base

import kotlinx.coroutines.*
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<TView : MvpView> : MvpPresenter<TView>(), CoroutineScope {
    private val job = SupervisorJob()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main + errorHandler

    open fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}