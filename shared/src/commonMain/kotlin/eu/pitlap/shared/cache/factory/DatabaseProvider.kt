package eu.pitlap.shared.cache.factory

import eu.pitlap.shared.cache.dao.articles.ArticleDAO
import eu.pitlap.shared.cache.dao.event.EventDAO
import eu.pitlap.shared.cache.dao.standings.StandingDAO
import eu.pitlap.shared.cache.dao.videos.VideoDAO
import eu.pitlap.shared.cache.database.Database
import eu.pitlap.shared.cache.getDatabaseFactory

internal object DatabaseProvider {
    private val database: Database by lazy {
        Database(databaseDriverFactory = getDatabaseFactory())
    }

    fun getEventDAO(): EventDAO {
        return get().eventDAO
    }

    fun getVideoDAO(): VideoDAO {
        return get().videoDAO
    }

    fun getArticleDAO(): ArticleDAO {
        return get().articleDAO
    }

    fun getStandingDAO(): StandingDAO {
        return get().standingDAO
    }

    fun get(): Database = database
}
