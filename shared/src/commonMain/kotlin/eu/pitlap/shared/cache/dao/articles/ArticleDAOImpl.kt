package eu.pitlap.shared.cache.dao.articles

import eu.pitlap.shared.cache.FeedItemMeta
import eu.pitlap.shared.cache.FeedItemMetaQueries
import eu.pitlap.shared.cache.FeedItemQueries
import eu.pitlap.shared.cache.mapper.FeedItemMapper
import eu.pitlap.shared.rss.domain.RSSFeedItem

internal class ArticleDAOImpl(
    private val articleQueries: FeedItemQueries,
    private val metaQueries: FeedItemMetaQueries
): ArticleDAO {
    override fun getAllArticles(): List<RSSFeedItem> =
        articleQueries.selectAllItems(FeedItemMapper::mapToFeedItem).executeAsList()

    override fun getArticleById(id: String): RSSFeedItem =
        articleQueries.selectItemById(id, FeedItemMapper::mapToFeedItem).executeAsOne()

    override fun getArticlesByFeedSource(feedUrl: String): List<RSSFeedItem> =
        articleQueries.selectAllItemsByFeedSource(feedUrl, FeedItemMapper::mapToFeedItem).executeAsList()

    override fun clearAndCreateArticles(feedUrl: String, articles: List<RSSFeedItem>) {
        articles.forEach {
            insertOrReplaceArticle(feedUrl, it)
        }
    }

    override fun getFeedMeta(): FeedItemMeta? =
        metaQueries.getMeta().executeAsOneOrNull()

    override fun insertMeta(timestamp: String) {
        metaQueries.clearMeta()
        metaQueries.insertMeta(timestamp)
    }

    private fun insertOrReplaceArticle(feedUrl: String, article: RSSFeedItem) {
        articleQueries.insertOrReplaceItem(
            id = article.id,
            title = article.title,
            channel_title = article.channelTitle,
            link = article.link,
            description = article.description,
            pub_date = article.pubDate,
            image_url = article.imageUrl,
            feed_url = feedUrl,
            created_at = article.pubDate,
            updated_at = article.pubDate,
        )
    }
}

