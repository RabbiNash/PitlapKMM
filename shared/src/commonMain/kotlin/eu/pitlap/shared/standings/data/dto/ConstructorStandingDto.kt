package eu.pitlap.shared.standings.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConstructorStandingDto(
    val position: Int,
    val positionText: String,
    val points: Int,
    val wins: Int,
    val constructorId: String,
    val constructorName: String
)
