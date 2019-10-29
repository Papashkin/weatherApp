package com.example.weatherapp.forecast.domain

import androidx.room.*
import com.example.weatherapp.forecast.models.ForecastPojo
import com.example.weatherapp.forecast.models.PlacePojo
import com.example.weatherapp.forecast.models.WindPojo
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import javax.inject.Inject

@Dao
interface ForecastDao {

    @Query("select * from forecast")
    suspend fun getAll() : List<ForecastPojo>

    @Insert
    suspend fun insert(forecastPojo: ForecastPojo): Long

    @Insert
    suspend fun insert(forecastPojos: List<ForecastPojo>)

    @Delete
    suspend fun delete(forecastPojo: ForecastPojo)

    @Delete
    suspend fun delete(forecastPojos: List<ForecastPojo>)

    @Query("select * from wind where date = :date")
    suspend fun getForecastWinds(date: String) : List<WindPojo>

    @Insert
    suspend fun insertWind(windPojo: WindPojo)

    @Insert
    suspend fun insertWind(windPojos: List<WindPojo>)

    @Delete
    suspend fun deleteWind(windPojo: WindPojo)

    @Delete
    suspend fun deleteWind(windPojos: List<WindPojo>)

    @Query("select * from place where date = :date")
    suspend fun getForecastPlaces(date: String) : List<PlacePojo>

    @Insert
    suspend fun insertPlace(placePojo: PlacePojo)

    @Insert
    suspend fun insertPlace(placePojos: List<PlacePojo>)

    @Delete
    suspend fun deletePlace(placePojo: PlacePojo)

    @Delete
    suspend fun deletePlace(placePojos: List<PlacePojo>)

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
class ForecastRepo @Inject constructor(
    @Provided private val forecastDao: ForecastDao
) {
    suspend fun getAll(): List<ForecastPojo> = forecastDao.getAll()
    suspend fun insert(forecastPojo: ForecastPojo) = forecastDao.insert(forecastPojo)
    suspend fun insert(forecastPojos: List<ForecastPojo>) = forecastDao.insert(forecastPojos)
    suspend fun delete(forecastPojo: ForecastPojo) = forecastDao.delete(forecastPojo)
    suspend fun delete(forecastPojos: List<ForecastPojo>) = forecastDao.delete(forecastPojos)

    suspend fun getForecstWinds(date: String) : List<WindPojo> = forecastDao.getForecastWinds(date)
    suspend fun insertWind(windPojo: WindPojo) = forecastDao.insertWind(windPojo)
    suspend fun insertWind(windPojos: List<WindPojo>) = forecastDao.insertWind(windPojos)
    suspend fun deleteWind(windPojo: WindPojo) = forecastDao.deleteWind(windPojo)
    suspend fun deleteWind(windPojos: List<WindPojo>) = forecastDao.deleteWind(windPojos)

    suspend fun getForecstPlaces(date: String) : List<PlacePojo> = forecastDao.getForecastPlaces(date)
    suspend fun insertPlace(placePojo: PlacePojo) = forecastDao.insertPlace(placePojo)
    suspend fun insertPlace(placePojos: List<PlacePojo>) = forecastDao.insertPlace(placePojos)
    suspend fun deletePlace(placePojo: PlacePojo) = forecastDao.deletePlace(placePojo)
    suspend fun deletePlace(placePojos: List<PlacePojo>) = forecastDao.deletePlace(placePojos)

    suspend fun deleteAll() = forecastDao.deleteAll()
}