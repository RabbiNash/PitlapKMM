package eu.pitlap.shared.weather.domain.mapper

import eu.pitlap.shared.weather.data.dto.WeatherDto
import eu.pitlap.shared.weather.domain.model.WeatherModel

fun WeatherDto.toModel(): WeatherModel {
    return WeatherModel(
        condition = condition,
        summary = aiSummary,
        temperature = temperature,
        precipitation = precipitation
    )
}
