package eu.pitlap.shared.navigation.destination.home

import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

sealed class HomeNavDestination(val route: String) {
    data object ArticleDetails : HomeNavDestination("article/{id}") {
        fun createRoute(feed: RSSFeedItem) = "article/${feed.id}"
    }

    data object VideoDetails : HomeNavDestination("video/{id}") {
        fun createRoute(video: YoutubeVideoModel) = "video/${video.videoId}"
    }
}

