package eu.pitlap.shared.cache

import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = PitlapDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.pitlapDBQueries
    private val articleFeedQueries = database.feedItemQueries
    private val ytFeedQueries = database.youtubeMetaQueries

    internal fun getAllEvents(): List<EventScheduleModel> =
        dbQuery.selectAllEventsInfo(::mapToEventScheduleModel).executeAsList()

    internal fun getEventByYearAndRound(year: Long, round: Long): EventScheduleModel? =
        dbQuery.selectEventByYearAndRound(year.toString(), round, ::mapToEventScheduleModel).executeAsOneOrNull()

    internal fun getEventByYear(year: Long): List<EventScheduleModel> =
        dbQuery.selectEventByYear(year.toString(), ::mapToEventScheduleModel).executeAsList()

    internal fun clearAndCreateEvents(events: List<EventScheduleModel>) = dbQuery.transaction {
        dbQuery.removeAllEVents()
        events.forEach { insertEvent(it) }
    }

    private fun insertEvent(event: EventScheduleModel) {
        dbQuery.insertEvent(
            round = event.round.toLong(),
            country = event.country,
            officialEventName = event.officialEventName,
            eventName = event.eventName,
            eventFormat = event.eventFormat,
            session1 = event.session1,
            session1DateUTC = event.session1DateUTC,
            session2 = event.session2,
            session2DateUTC = event.session2DateUTC,
            session3 = event.session3,
            session3DateUTC = event.session3DateUTC,
            session4 = event.session4,
            session4DateUTC = event.session4DateUTC,
            session5 = event.session5,
            session5DateUTC = event.session5DateUTC,
            year = event.year,
        )
    }

    internal fun getAllVideos(): List<YoutubeVideoModel> =
        dbQuery.selectAllVideos(::mapToVideoModel).executeAsList()

    internal fun getVideoById(id: String): YoutubeVideoModel =
        dbQuery.selectVideoById(id, ::mapToVideoModel).executeAsOne()

    internal fun getVideosByChannelName(channelName: String): List<YoutubeVideoModel> =
        dbQuery.selectVideoByChannelName(channelName, ::mapToVideoModel).executeAsList()

    internal fun clearAndCreateVideos(channelName: String, videos: List<YoutubeVideoModel>) = dbQuery.transaction {
        videos.forEach { insertOrReplaceVideo(channelName, it) }
    }

    private fun insertOrReplaceVideo(channelName: String, video: YoutubeVideoModel) {
        dbQuery.insertOrReplaceVideo(
            video_id = video.videoId,
            thumbnail_url = video.thumbnailUrl,
            title = video.title,
            description = video.description,
            channel_name = channelName,
            created_at = video.publishedAt,
            updated_at = video.publishedAt,
        )
    }

    internal fun getAllArticles(): List<RSSFeedItem> =
        articleFeedQueries.selectAllItems(::mapToFeedItem).executeAsList()

    internal fun getArticleById(id: String): RSSFeedItem =
        articleFeedQueries.selectItemById(id, ::mapToFeedItem).executeAsOne()

    internal fun getArticlesByFeedSource(feedUrl: String): List<RSSFeedItem> =
        articleFeedQueries.selectAllItemsByFeedSource(feedUrl, ::mapToFeedItem).executeAsList()

    internal fun clearAndCreateArticles(feedUrl: String, articles: List<RSSFeedItem>) = dbQuery.transaction {
        articles.forEach { insertOrReplaceArticle(feedUrl, it) }
    }

    private fun insertOrReplaceArticle(feedUrl: String, article: RSSFeedItem) {
        articleFeedQueries.insertOrReplaceItem(
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

    internal fun getFeedMeta(): FeedItemMeta? =
        database.feedItemMetaQueries.getMeta().executeAsOneOrNull()

    internal fun getYTMeta(): YoutubeMeta? =
        ytFeedQueries.getMeta().executeAsOneOrNull()

    internal fun insertMeta(timestamp: String) {
        database.feedItemMetaQueries.clearMeta()
        database.feedItemMetaQueries.insertMeta(timestamp)
    }

    private fun mapToVideoModel(
        videoId: String,
        thumbnailUrl: String?,
        title: String,
        description: String,
        channelName: String,
        createdAt: String,
        updatedAt: String,
    ): YoutubeVideoModel = YoutubeVideoModel(
        thumbnailUrl = thumbnailUrl,
        title = title,
        description = description,
        videoId = videoId,
        publishedAt = createdAt,
    )

    private fun mapToFeedItem(
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

    private fun mapToEventScheduleModel(
        round: Long,
        country: String,
        officialEventName: String,
        eventName: String,
        eventFormat: String,
        session1: String,
        session1DateUTC: String?,
        session2: String,
        session2DateUTC: String?,
        session3: String,
        session3DateUTC: String?,
        session4: String,
        session4DateUTC: String?,
        session5: String,
        session5DateUTC: String?,
        year: String,
    ): EventScheduleModel = EventScheduleModel(
        round = round.toInt(),
        country = country,
        officialEventName = officialEventName,
        eventName = eventName,
        eventFormat = eventFormat,
        session1 = session1,
        session1DateUTC = session1DateUTC,
        session2 = session2,
        session2DateUTC = session2DateUTC,
        session3 = session3,
        session3DateUTC = session3DateUTC,
        session4 = session4,
        session4DateUTC = session4DateUTC,
        session5 = session5,
        session5DateUTC = session5DateUTC,
        year = year,
    )
}
