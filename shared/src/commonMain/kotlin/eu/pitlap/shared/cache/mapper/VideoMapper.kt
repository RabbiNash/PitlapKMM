package eu.pitlap.shared.cache.mapper

import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

object VideoMapper {
    fun mapToVideoModel(
        videoId: String,
        thumbnailUrl: String?,
        title: String,
        description: String,
        channelName: String,
        createdAt: String,
        updatedAt: String,
    ): YoutubeVideoModel = YoutubeVideoModel(
        thumbnailUrl = thumbnailUrl,
        title = title,
        description = description,
        videoId = videoId,
        publishedAt = createdAt,
    )
}
