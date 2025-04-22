package eu.pitlap.shared.weather.domain.repository

import eu.pitlap.shared.weather.domain.model.WeatherModel

internal interface WeatherRepository {
    @Throws(Throwable::class)
    suspend fun getWeatherSummary(year: Int, round: Int): WeatherModel
}
