package eu.pitlap.shared.modules

object Pitlap {

    private val instance: PitlapService by lazy {
        PitlapServiceImpl()
    }

    fun getService(): PitlapService = instance
}
