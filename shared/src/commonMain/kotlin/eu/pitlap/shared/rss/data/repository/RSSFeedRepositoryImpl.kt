package eu.pitlap.shared.rss.data.repository
import eu.pitlap.shared.cache.dao.articles.ArticleDAO
import eu.pitlap.shared.cache.factory.DatabaseProvider
import eu.pitlap.shared.rss.data.provider.RSSProvider
import eu.pitlap.shared.rss.data.provider.RSSProviderImpl
import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.rss.domain.repository.RSSFeedRepository
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal class RSSFeedRepositoryImpl(
    private val rssProvider: RSSProvider = RSSProviderImpl(),
    private val dao: ArticleDAO = DatabaseProvider.getArticleDAO(),
): RSSFeedRepository {
    private val ttl = Duration.parse("PT1H")

    @OptIn(ExperimentalTime::class)
    override suspend fun getRSSFeed(url: String, forceRefresh: Boolean): List<RSSFeedItem> {
        val cachedEvents = dao.getArticlesByFeedSource(url)
        val meta = dao.getFeedMeta()
        val events = if (meta != null && !forceRefresh && Clock.System.now() - Instant.parse(meta.last_fetched) < ttl) {
            cachedEvents
        } else {
            emptyList()
        }
        return events
            .ifEmpty {
                val channel = rssProvider.getRSSFeed(url)
                val now = Clock.System.now().toString()
                val articles = channel.items.map {
                    RSSFeedItem(
                        id = it.guid ?: "",
                        title = it.title ?: "",
                        channelTitle = channel.title ?: "",
                        link = it.link ?: "",
                        description = removeHtmlTagsPreserveLineBreaks( it.description ?: ""),
                        pubDate = it.pubDate ?: "",
                        imageUrl = it.image ?: "",
                    )
                }
                dao.clearAndCreateArticles(url, articles)
                dao.insertMeta(now)
                articles
            }
    }

    override suspend fun getArticleFeedById(id: String): RSSFeedItem? {
        return dao.getArticleById(id)
    }

    private fun removeHtmlTagsPreserveLineBreaks(html: String): String {
        return html
            .replace(Regex("<br\\s*/?>", RegexOption.IGNORE_CASE), "\n")
            .replace(Regex("<[^>]*>"), "")
    }
}
