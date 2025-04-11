package eu.pitlap.shared.race.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class TrackSummaryDto (
    val fact: String,
    val track: String,
    val circuitLengthKm: Float,
    val firstRace: Int
)
