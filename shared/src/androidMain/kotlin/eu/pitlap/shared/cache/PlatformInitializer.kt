package eu.pitlap.shared.cache

import android.content.Context
import eu.pitlap.shared.cache.AndroidDatabaseDriverFactory
import eu.pitlap.shared.cache.DatabaseDriverFactory

private lateinit var appContext: Context

fun initKmmDependencies(context: Context) {
    appContext = context.applicationContext
}

actual fun getDatabaseFactory(): DatabaseDriverFactory {
    check(::appContext.isInitialized) { "KMM dependencies not initialized. Call initKmmDependencies() first." }
    return AndroidDatabaseDriverFactory(appContext)
}
