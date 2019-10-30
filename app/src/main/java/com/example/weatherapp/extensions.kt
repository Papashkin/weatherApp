package com.example.weatherapp

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