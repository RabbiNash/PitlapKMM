package eu.pitlap.shared.videos.domain.model

data class YoutubeVideoModel (
    val thumbnailUrl: String?,
    val title: String,
    val description: String,
    val videoId: String,
    val publishedAt: String,
)
