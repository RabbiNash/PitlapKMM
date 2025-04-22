package eu.pitlap.shared.videos.domain.mapper

import eu.pitlap.shared.videos.data.dto.YoutubeVideoDto
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

internal class YoutubeVideoMapper {
    fun mapToYoutubeVideoModel(
        videos: List<YoutubeVideoDto>
    ): List<YoutubeVideoModel> {
        return videos.map {
            YoutubeVideoModel(
                title = it.title,
                description = it.description,
                thumbnailUrl = it.thumbnails.maxres?.url ?: it.thumbnails.high.url,
                videoId = it.resourceID.videoID,
                publishedAt = it.publishedAt
            )
        }
    }
}
