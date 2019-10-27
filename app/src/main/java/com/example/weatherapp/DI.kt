package com.example.weatherapp

import android.util.Log
import com.example.weatherapp.forecast.ForecastFragment
import com.moczul.ok2curl.CurlInterceptor
import dagger.Component
import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {
    companion object {
        private val tag = AppModule::class.java.simpleName
    }

    fun weatherService() : WeatherService {

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(CurlInterceptor {curl ->
                Log.i(tag, curl)
            })
            .build()

        val service = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return service.create(WeatherService::class.java)
    }
}

@Component(modules = [AppModule::class])
interface WeatherAppComponent {
    fun inject(fragment: ForecastFragment)
}