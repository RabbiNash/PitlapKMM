package eu.pitlap.shared.home.state

import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    val feeds: List<RSSFeedItem> = emptyList(),
    val videos: List<YoutubeVideoModel> = emptyList(),
    val error: String? = null
)

