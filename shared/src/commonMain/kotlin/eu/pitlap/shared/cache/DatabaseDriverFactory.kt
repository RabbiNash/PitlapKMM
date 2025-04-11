package eu.pitlap.shared.cache

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun getDatabaseFactory() : DatabaseDriverFactory
