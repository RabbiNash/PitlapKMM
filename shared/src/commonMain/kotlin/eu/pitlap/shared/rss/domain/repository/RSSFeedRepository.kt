package eu.pitlap.shared.rss.domain.repository

import eu.pitlap.shared.rss.domain.RSSFeedItem

internal interface RSSFeedRepository {
    suspend fun getRSSFeed(url: String, forceRefresh: Boolean = false): List<RSSFeedItem>
    suspend fun getArticleFeedById(id: String): RSSFeedItem?
}
