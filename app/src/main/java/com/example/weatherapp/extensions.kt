package com.example.weatherapp

import com.example.weatherapp.observation.models.ObservationPOJO
import com.example.weatherapp.observation.models.ObservationsModel
import com.example.weatherapp.observation.models.StationDTO
import com.example.weatherapp.forecast.models.*

fun ForecastDTO.convertToForecastPojo(): ForecastPOJO {
    return ForecastPOJO(
        this.date, this.day?.convertToPojo(this.date), this.night?.convertToPojo(this.date)
    )
}

fun DayNightDTO.convertToPojo(date: String): DayNightPOJO {
    return DayNightPOJO(
        date = date,
        phenomenon = this.phenomenon,
        tempmin = this.tempmin,
        tempmax = this.tempmax,
        peipsi = this.peipsi,
        sea = this.sea,
        text = this.text
    )
}

fun WindDTO.convertToPojo(date: String, forecastId: Long, isDay: Boolean): WindPOJO {
    return WindPOJO(
        windId = 0L,
        date = date,
        name = this.name,
        direction = this.direction,
        speedmin = this.speedmin,
        speedmax = this.speedmax,
        forecastId = forecastId,
        isDay = isDay
    )
}

fun PlaceDTO.convertToPojo(date: String, forecastId: Long, isDay: Boolean): PlacePOJO {
    return PlacePOJO(
        placeId = 0L,
        date = date,
        name = this.name,
        phenomenon = this.phenomenon,
        tempmin = this.tempmin,
        tempmax = this.tempmax,
        forecastId = forecastId,
        isDay = isDay
    )
}

fun WindPOJO.convertToDTO(): WindDTO {
    return WindDTO(this.name, this.direction, this.speedmin, this.speedmax)
}

fun PlacePOJO.convertToDTO(): PlaceDTO {
    return PlaceDTO(this.name, this.phenomenon, this.tempmin, this.tempmax)
}

fun DayNightPOJO.convertToDTO(places: List<PlaceDTO>?, winds: List<WindDTO>?): DayNightDTO {
    return DayNightDTO(
        phenomenon = this.phenomenon,
        tempmin = this.tempmin,
        tempmax = this.tempmax,
        text = this.text,
        sea = this.sea,
        peipsi = this.peipsi,
        places = places ?: listOf(),
        winds = winds ?: listOf()
    )
}

fun ForecastPOJO.convertToDTO(day: DayNightDTO?, night: DayNightDTO?): ForecastDTO {
    return ForecastDTO(date = this.date, day = day, night = night)
}

fun ObservationsModel.convertToPOJO(): List<ObservationPOJO> {
    return this.observations.map { it.convertToPOJO(this.timestamp) }
}

fun StationDTO.convertToPOJO(timestamp: String): ObservationPOJO {
    return ObservationPOJO(
        id = 0L,
        name = this.name,
        wmocode = this.wmocode,
        longitude = this.longitude,
        latitude = this.latitude,
        phenomenon = this.phenomenon,
        visibility = this.visibility,
        precipitations = this.precipitations,
        airpressure = this.airpressure,
        relativehumidity = this.relativehumidity,
        airtemperature = this.airtemperature,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
        windspeedmax = this.windspeedmax,
        waterlevel = this.waterlevel,
        waterlevel_eh2000 = this.waterlevel_eh2000,
        watertemperature = this.watertemperature,
        uvindex = this.uvindex,
        timestamp = timestamp
    )
}

fun ObservationPOJO.convertToDTO(): StationDTO {
    return StationDTO(
            name = this.name,
            wmocode = this.wmocode,
            longitude = this.longitude,
            latitude = this.latitude,
            phenomenon = this.phenomenon,
            visibility = this.visibility,
            precipitations = this.precipitations,
            airpressure = this.airpressure,
            relativehumidity = this.relativehumidity,
            airtemperature = this.airtemperature,
            winddirection = this.winddirection,
            windspeed = this.windspeed,
            windspeedmax = this.windspeedmax,
            waterlevel = this.waterlevel,
            waterlevel_eh2000 = this.waterlevel_eh2000,
            watertemperature = this.watertemperature,
            uvindex = this.uvindex
    )
}