package eu.pitlap.shared.weather.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val year: Int,
    val round: Int,
    val aiSummary: String,
    val condition: String,
    val summary: String,
    val temperature: Float,
    val precipitation: Float
)
