package com.example.weatherapp.observation.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.observation.models.ObservationPOJO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import javax.inject.Inject

@Dao
interface ObservationDao {

    @Query("select timestamp from observation limit 1")
    suspend fun getTimestamp(): String

    @Query("select * from observation where name like '%' || :name || '%'")
    suspend fun getByName(name: String): List<ObservationPOJO>?

    @Insert
    suspend fun insert(observations: List<ObservationPOJO>)

    @Query("delete from observation")
    suspend fun deleteAll()
}

@AutoFactory
class ObservationRepository @Inject constructor(
    @Provided private val observationDao: ObservationDao
) {
    suspend fun getTimestamp() = observationDao.getTimestamp()
    suspend fun getByName(name: String) = observationDao.getByName(name)
    suspend fun deleteAll() = observationDao.deleteAll()
    suspend fun insert(observations: List<ObservationPOJO>) = observationDao.insert(observations)
}