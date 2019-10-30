package com.example.weatherapp.forecast.data

import androidx.room.*
import com.example.weatherapp.forecast.models.ForecastPOJO
import com.example.weatherapp.forecast.models.PlacePOJO
import com.example.weatherapp.forecast.models.WindPOJO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import javax.inject.Inject

@Dao
interface ForecastDao {

    @Query("select * from forecast")
    suspend fun getAll() : List<ForecastPOJO>

    @Insert
    suspend fun insert(forecastPOJO: ForecastPOJO): Long

    @Query("select * from wind where date = :date")
    suspend fun getForecastWinds(date: String) : List<WindPOJO>

    @Insert
    suspend fun insertWind(windPOJOS: List<WindPOJO>)

    @Query("select * from place where date = :date")
    suspend fun getForecastPlaces(date: String) : List<PlacePOJO>

    @Insert
    suspend fun insertPlace(placePOJOS: List<PlacePOJO>)

    @Query("delete from forecast")
    suspend fun deleteAllForecasts()

    @Query("delete from wind")
    suspend fun deleteAllWinds()

    @Query("delete from place")
    suspend fun deleteAllPlaces()

    @Transaction
    suspend fun deleteAll() {
        deleteAllPlaces()
        deleteAllWinds()
        deleteAllForecasts()
    }
}

@AutoFactory
class ForecastRepository @Inject constructor(
    @Provided private val forecastDao: ForecastDao
) {
    // forecasts
    suspend fun getAll(): List<ForecastPOJO> = forecastDao.getAll()
    suspend fun insert(forecastPOJO: ForecastPOJO) = forecastDao.insert(forecastPOJO)

    // winds
    suspend fun getForecastWinds(date: String) : List<WindPOJO> = forecastDao.getForecastWinds(date)
    suspend fun insertWind(windPOJOS: List<WindPOJO>) = forecastDao.insertWind(windPOJOS)

    // places
    suspend fun getForecastPlaces(date: String) : List<PlacePOJO> = forecastDao.getForecastPlaces(date)
    suspend fun insertPlace(placePOJOS: List<PlacePOJO>) = forecastDao.insertPlace(placePOJOS)

    suspend fun deleteAll() = forecastDao.deleteAll()
}