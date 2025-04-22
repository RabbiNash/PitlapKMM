package eu.pitlap.shared.videos.domain.repository

import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

internal interface YoutubeVideosRepository {
    @Throws(Throwable::class)
    suspend fun getVideos(channelName: String): List<YoutubeVideoModel>

    @Throws (Throwable::class)
    suspend fun getRankedVideos(): List<YoutubeVideoModel>

    @Throws (Throwable::class)
    suspend fun getVideoById(videoId: String): YoutubeVideoModel?
}
