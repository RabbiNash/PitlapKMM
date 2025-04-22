package eu.pitlap.shared.weather.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.videos.data.dto.YoutubeVideoDto
import eu.pitlap.shared.weather.data.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pitlap.eu"

internal class WeatherDataSourceImpl (
    private val client: HttpClient = HttpClientProvider.client
): WeatherDataSource {
    override suspend fun getWeather(year: Int, round: Int): Result<WeatherDto, ApiError.Remote> {
        return safeCall<WeatherDto> {
            client.get(urlString = "$BASE_URL/weather/$year/$round/R")
        }
    }
}
