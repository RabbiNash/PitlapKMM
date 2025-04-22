package eu.pitlap.shared.rss.domain

data class RSSFeedItem(
    val id: String,
    val title: String,
    val channelTitle: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val imageUrl: String?
)
