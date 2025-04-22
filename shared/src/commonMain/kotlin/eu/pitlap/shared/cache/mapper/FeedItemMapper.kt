package eu.pitlap.shared.cache.mapper

import eu.pitlap.shared.rss.domain.RSSFeedItem

object FeedItemMapper {
    fun mapToFeedItem(
        id: String,
        title: String,
        channel_title: String,
        link: String,
        description: String,
        pub_date: String,
        image_url: String?,
        feed_url: String,
        created_at: String,
        updated_at: String
    ): RSSFeedItem = RSSFeedItem(
        id = id,
        title = title,
        link = link,
        description = description,
        channelTitle = channel_title,
        pubDate = pub_date,
        imageUrl = image_url,
    )
}
