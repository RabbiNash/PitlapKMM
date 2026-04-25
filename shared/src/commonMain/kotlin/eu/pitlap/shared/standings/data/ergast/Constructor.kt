package eu.pitlap.shared.standings.data.ergast

import kotlinx.serialization.Serializable

@Serializable
data class Constructor(
    val constructorId: String,
    val url: String,
    val name: String,
    val nationality: String
)
