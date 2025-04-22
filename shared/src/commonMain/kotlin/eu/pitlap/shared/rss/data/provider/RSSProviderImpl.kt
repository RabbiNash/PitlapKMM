package eu.pitlap.shared.rss.data.provider

import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel

internal class RSSProviderImpl(
    private val parser: RssParser = RssParser()
): RSSProvider {
    override suspend fun getRSSFeed(url: String): RssChannel {
        return parser.getRssChannel(url)
    }
}
