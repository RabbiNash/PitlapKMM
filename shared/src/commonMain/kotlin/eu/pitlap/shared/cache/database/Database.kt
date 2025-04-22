package eu.pitlap.shared.cache.database

import eu.pitlap.shared.cache.DatabaseDriverFactory
import eu.pitlap.shared.cache.PitlapDB
import eu.pitlap.shared.cache.dao.articles.ArticleDAOImpl
import eu.pitlap.shared.cache.dao.event.EventDAOImpl
import eu.pitlap.shared.cache.dao.standings.StandingDAOImpl
import eu.pitlap.shared.cache.dao.videos.VideoDAOImpl

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = PitlapDB(databaseDriverFactory.createDriver())

    val eventDAO = EventDAOImpl(
        eventQueries = database.pitlapDBQueries,
    )

    val videoDAO = VideoDAOImpl(
        videoQueries = database.pitlapDBQueries,
        metaQueries = database.youtubeMetaQueries,
    )

    val articleDAO = ArticleDAOImpl(
        articleQueries = database.feedItemQueries,
        metaQueries = database.feedItemMetaQueries
    )

    val standingDAO = StandingDAOImpl(
        driverStandingsQueries = database.driverStandingsQueries,
        constructorStandingsQueries = database.constructorStandingsQueries,
        driverStandingMetaQueries = database.driverStandingMetaQueries,
        constructorStandingMetaQueries = database.constructorStandingMetaQueries
    )
}
