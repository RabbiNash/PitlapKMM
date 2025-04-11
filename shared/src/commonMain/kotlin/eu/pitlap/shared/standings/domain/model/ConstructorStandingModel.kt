package eu.pitlap.shared.standings.domain.model

data class ConstructorStandingModel(
    val position: Int,
    val positionText: String,
    val points: Int,
    val wins: Int,
    val constructorId: String,
    val constructorName: String
)

