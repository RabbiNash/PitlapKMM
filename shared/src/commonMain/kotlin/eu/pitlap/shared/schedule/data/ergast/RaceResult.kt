package eu.pitlap.shared.schedule.data.ergast

import eu.pitlap.shared.standings.data.ergast.Constructor
import eu.pitlap.shared.standings.data.ergast.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceResult(
    val position: String,
    val positionText: String,
    val points: String,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructor")
    val constructor: Constructor,
    val grid: String,
    val status: String
)
