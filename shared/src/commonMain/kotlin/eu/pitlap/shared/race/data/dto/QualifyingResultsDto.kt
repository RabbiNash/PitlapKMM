package eu.pitlap.shared.race.data.dto

internal data class QualifyingResultsDto (
    val key: String,
    val eventName: String,
    val results: List<QualifyingResultDto>
)

internal data class QualifyingResultDto(
    val teamName: String,
    val headshotUrl: String,
    val q1: String?,
    val q2: String?,
    val q3: String?,
    val position: Int,
    val fullName: String
)
