package eu.pitlap.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform