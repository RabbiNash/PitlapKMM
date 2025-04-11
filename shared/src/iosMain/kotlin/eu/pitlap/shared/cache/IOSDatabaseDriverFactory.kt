package eu.pitlap.shared.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(PitlapDB.Schema, "pitlap.db")
    }
}

actual fun getDatabaseFactory(): DatabaseDriverFactory {
    return IOSDatabaseDriverFactory()
}
