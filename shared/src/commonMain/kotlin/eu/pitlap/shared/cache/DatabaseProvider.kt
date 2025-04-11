package eu.pitlap.shared.cache

internal object DatabaseProvider {
    private val database: Database by lazy {
        Database(databaseDriverFactory = getDatabaseFactory())
    }

    fun get(): Database = database
}
