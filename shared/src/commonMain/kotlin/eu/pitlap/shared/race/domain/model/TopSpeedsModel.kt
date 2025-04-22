package eu.pitlap.shared.race.domain.model

data class TopSpeedsModel(
    val year: Int,
    val round: Int,
    val sessionName: String,
    val eventFormat: String,
    val speeds: List<TopSpeedModel>
)

data class TopSpeedModel(
    val driver: String,
    val speed: Double,
)
