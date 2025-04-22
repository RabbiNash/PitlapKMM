package eu.pitlap.shared.videos.domain.repository

import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

internal interface YoutubeVideosRepository {
    @Throws(Throwable::class)
    suspend fun getVideos(channelName: String, forceRefresh: Boolean): List<YoutubeVideoModel>

    @Throws (Throwable::class)
    suspend fun getRankedVideos(forceRefresh: Boolean): List<YoutubeVideoModel>

    @Throws (Throwable::class)
    suspend fun getVideoById(videoId: String): YoutubeVideoModel?
}
