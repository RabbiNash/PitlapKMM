package eu.pitlap.shared.weather.domain.model

data class WeatherModel(
    val condition: String,
    val summary: String,
    val temperature: Float,
    val precipitation: Float
)
