package eu.pitlap.shared.race.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TopSpeedsDto(
    val year: Int,
    val round: Int,
    val sessionName: String,
    val eventFormat: String,
    val speeds: List<SpeedDto>
)

@Serializable
internal data class SpeedDto(
    val driver: String,
    @SerialName("topSpeed")
    val speed: Double,
)
