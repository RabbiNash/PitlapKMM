package eu.pitlap.shared.standings.models

import androidx.compose.runtime.Immutable

@Immutable
data class StandingRowUiModel(
    val position: String,
    val fullName: String?,
    val constructorName: String,
    val points: String,
    val wins: String
)
