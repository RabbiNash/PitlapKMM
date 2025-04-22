package eu.pitlap.shared.videos.state

import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

data class VideoDetailScreenState(
    val isLoading: Boolean = false,
    val video: YoutubeVideoModel? = null,
    val error: String? = null
)

