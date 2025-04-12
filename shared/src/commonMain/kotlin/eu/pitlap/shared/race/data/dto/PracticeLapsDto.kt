package eu.pitlap.shared.race.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class PracticeLapsDto(
    val year: Int,
    val round: Int,
    val sessionName: String,
    val eventFormat: String,
    val laps: List<LapDto>
)

@Serializable
internal data class LapDto(
    val driver: String,
    val headshotUrl: String,
    val compound: String,
    val lapTime: String,
    val lapNumber: Int,
    val fullName: String
)
