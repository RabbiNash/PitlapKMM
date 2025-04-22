package eu.pitlap.shared.weather.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.videos.data.dto.YoutubeVideoDto
import eu.pitlap.shared.weather.data.dto.WeatherDto

internal interface WeatherDataSource {
    suspend fun getWeather(year: Int, round: Int): Result<WeatherDto, ApiError.Remote>
}
