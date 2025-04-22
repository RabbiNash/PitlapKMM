package eu.pitlap.shared.articles.state

import eu.pitlap.shared.rss.domain.RSSFeedItem

data class ArticleDetailScreenState(
    val isLoading: Boolean = false,
    val article: RSSFeedItem? = null,
    val error: String? = null
)

