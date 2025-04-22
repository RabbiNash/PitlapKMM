package eu.pitlap.shared.rss.data.provider

import com.prof18.rssparser.model.RssChannel

interface RSSProvider {
    suspend fun getRSSFeed(url: String): RssChannel
}
