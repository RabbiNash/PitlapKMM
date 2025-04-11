package eu.pitlap.shared.standings.domain.model

data class DriverStandingModel(
    val position: Int,
    val positionText: String,
    val points: Int,
    val wins: Int,
    val driverId: String,
    val driverNumber: Int,
    val givenName: String,
    val familyName: String,
    val constructorName: String
)
