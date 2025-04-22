package eu.pitlap.shared.videos.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.videos.data.dto.YoutubeVideoDto

internal interface YoutubeVideosDataSource {
    suspend fun getVideos(channelName: String): Result<List<YoutubeVideoDto>, ApiError.Remote>
}
