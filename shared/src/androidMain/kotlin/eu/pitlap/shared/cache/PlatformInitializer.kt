package eu.pitlap.shared.cache

import android.content.Context

private lateinit var appContext: Context

fun initKmmDependencies(context: Context) {
    appContext = context.applicationContext
}

actual fun getDatabaseFactory(): DatabaseDriverFactory {
    check(::appContext.isInitialized) { "KMM dependencies not initialized. Call initKmmDependencies() first." }
    return AndroidDatabaseDriverFactory(appContext)
}
