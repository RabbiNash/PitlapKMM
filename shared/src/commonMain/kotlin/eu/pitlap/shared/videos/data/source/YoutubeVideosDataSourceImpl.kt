package eu.pitlap.shared.videos.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.videos.data.dto.YoutubeVideoDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pitlap.eu"

internal class YoutubeVideosDataSourceImpl (
    private val client: HttpClient = HttpClientProvider.client
): YoutubeVideosDataSource {
    override suspend fun getVideos(channelName: String): Result<List<YoutubeVideoDto>, ApiError.Remote> {
        return safeCall<List<YoutubeVideoDto>> {
            client.get(urlString = "$BASE_URL/youtube/$channelName")
        }
    }
}
