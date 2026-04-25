package eu.pitlap.shared.core.data.models.ergast.tables

import eu.pitlap.shared.schedule.data.ergast.Race
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceTable(
    val season: String,
    @SerialName("Races")
    val races: List<Race>
): ErgastTable
