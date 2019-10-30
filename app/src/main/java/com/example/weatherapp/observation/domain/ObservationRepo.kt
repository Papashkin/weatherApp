package com.example.weatherapp.observation.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.observation.models.ObservationPOJO
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import javax.inject.Inject

@Dao
interface ObservationDao {

    @Query("select * from observation")
    suspend fun getAll(): List<ObservationPOJO>

    @Query("select timestamp from observation limit 1")
    suspend fun getTimestamp(): String

    @Query("select * from observation where charindex(:name, name) > 0")   // it.name.contains(name)
    suspend fun getByName2(name: String): List<ObservationPOJO>?

    @Query("select * from observation where name like '%' || :name || '%'")   // it.name.contains(name)
//    @Query("select * from observation where name like :name")   // it.name.contains(name)
    suspend fun getByName(name: String): List<ObservationPOJO>?

    @Insert
    suspend fun insert(observations: List<ObservationPOJO>)

    @Query("delete from observation")
    suspend fun deleteAll()
}

@AutoFactory
class ObservationRepo @Inject constructor(
    @Provided private val observationDao: ObservationDao
) {
    suspend fun getAll() = observationDao.getAll()
    suspend fun getTimestamp() = observationDao.getTimestamp()
    suspend fun getByName(name: String) = observationDao.getByName(name)
    suspend fun getByName2(name: String) = observationDao.getByName2(name)
    suspend fun deleteAll() = observationDao.deleteAll()
    suspend fun insert(observations: List<ObservationPOJO>) = observationDao.insert(observations)
}