package eu.pitlap.shared.home.state

import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

sealed class HomeScreenEvent {
    data object LoadData : HomeScreenEvent()
    data class LoadVideo(val video: YoutubeVideoModel) : HomeScreenEvent()
    data class LoadArticle(val article: RSSFeedItem) : HomeScreenEvent()
}
