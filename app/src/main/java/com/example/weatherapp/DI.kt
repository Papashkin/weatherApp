package com.example.weatherapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.weatherapp.observation.ObservationFragment
import com.example.weatherapp.observation.domain.ObservationDao
import com.example.weatherapp.forecast.ForecastFragment
import com.example.weatherapp.forecast.domain.ForecastDao
import com.moczul.ok2curl.CurlInterceptor
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val context: Context) {
    companion object {
        private val tag = AppModule::class.java.simpleName
    }

    @Provides
    fun provideWeatherService(): WeatherService {

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(CurlInterceptor { curl ->
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

    @Provides
    fun provideWeatherDatabase(): WeatherDatabase {

        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weatherMooncascade"
        ).build()
    }

    @Provides
    fun forecastDao(database: WeatherDatabase): ForecastDao {
        return database.forecastDao()
    }

    @Provides
    fun observationDao(database: WeatherDatabase): ObservationDao {
        return database.observationDao()
    }
}

@Component(modules = [AppModule::class])
interface WeatherAppComponent {
    fun inject(fragment: ForecastFragment)
    fun inject(fragment: ObservationFragment)
}