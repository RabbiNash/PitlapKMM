package eu.pitlap.shared.standings.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DriverStandingDto(
    val position: Int,
    val positionText: String,
    val points: Int,
    val wins: Int,
    @SerialName("driverId")
    val driverID: String,
    val driverNumber: Int,
    val givenName: String,
    val familyName: String,
    val constructorName: String
)
