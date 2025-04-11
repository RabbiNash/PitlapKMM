package eu.pitlap.shared.app

import android.app.Application
import eu.pitlap.shared.cache.initKmmDependencies

class PitlapApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKmmDependencies(this)
    }
}
