package eu.pitlap.shared.cache.dao.articles

import eu.pitlap.shared.cache.FeedItemMeta
import eu.pitlap.shared.rss.domain.RSSFeedItem

internal interface ArticleDAO {
    fun getAllArticles(): List<RSSFeedItem>
    fun getArticleById(id: String): RSSFeedItem
    fun getArticlesByFeedSource(feedUrl: String): List<RSSFeedItem>
    fun clearAndCreateArticles(feedUrl: String, articles: List<RSSFeedItem>)
    fun getFeedMeta(): FeedItemMeta?
    fun insertMeta(timestamp: String)
}
