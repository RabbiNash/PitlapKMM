package eu.pitlap.shared.race.domain.model

data class TrackSummaryModel(
    val fact: String,
    val track: String,
    val circuitLengthKm: Float,
    val firstRace: Int
)
