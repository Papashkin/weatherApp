package com.example.weatherapp

import android.util.Log
import com.example.weatherapp.cityCurrentForecast.CityCurrentForecastFragment
import com.example.weatherapp.forecast.ForecastFragment
import com.moczul.ok2curl.CurlInterceptor
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {
    companion object {
        private val tag = AppModule::class.java.simpleName
    }

    @Provides
    fun weatherService() : WeatherService {

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(CurlInterceptor {curl ->
                Log.i(tag, curl)
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherService::class.java)
    }
}

@Component(modules = [AppModule::class])
interface WeatherAppComponent {
    fun inject(fragment: ForecastFragment)
    fun inject(fragment: CityCurrentForecastFragment)
}