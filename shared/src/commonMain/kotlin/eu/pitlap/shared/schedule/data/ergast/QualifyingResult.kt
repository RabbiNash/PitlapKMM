package eu.pitlap.shared.schedule.data.ergast

import eu.pitlap.shared.standings.data.ergast.Constructor
import eu.pitlap.shared.standings.data.ergast.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QualifyingResult(
    val position: String,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructor")
    val constructor: Constructor,
    @SerialName("Q1")
    val q1: String? = null,
    @SerialName("Q2")
    val q2: String? = null,
    @SerialName("Q3")
    val q3: String? = null
)
