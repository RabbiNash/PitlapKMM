package eu.pitlap.shared.race.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class RaceSummaryDto(
    val key: String,
    val round: Int,
    val name: String,
    val year: Int,
    val summary: String
)
