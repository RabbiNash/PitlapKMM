package eu.pitlap.shared.standings.data.ergast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverStanding(
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructors")
    val constructors: List<Constructor>
)
