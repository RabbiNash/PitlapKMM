package eu.pitlap.shared.standings.data.ergast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsList(
    val season: String,
    val round: String,
    @SerialName("DriverStandings")
    val driverStandings: List<DriverStanding> = emptyList(),
    @SerialName("ConstructorStandings")
    val constructorStandings: List<ConstructorStanding> = emptyList()
)
