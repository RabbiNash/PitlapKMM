package eu.pitlap.shared.core.data.models.ergast.tables

import eu.pitlap.shared.standings.data.ergast.StandingsList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsTable(
    val season: String,
    val round: String,
    @SerialName("StandingsLists")
    val standingsLists: List<StandingsList>
) : ErgastTable
