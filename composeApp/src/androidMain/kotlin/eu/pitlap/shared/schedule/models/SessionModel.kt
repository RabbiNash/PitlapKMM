package eu.pitlap.shared.schedule.models

import androidx.compose.runtime.Immutable

@Immutable
data class SessionModel(
    val round: Int,
    val year: Int,
    val sessionName: String
)
