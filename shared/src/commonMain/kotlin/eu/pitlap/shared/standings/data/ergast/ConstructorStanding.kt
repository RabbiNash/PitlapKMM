package eu.pitlap.shared.standings.data.ergast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorStanding(
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    @SerialName("Constructor")
    val constructor: Constructor
)
