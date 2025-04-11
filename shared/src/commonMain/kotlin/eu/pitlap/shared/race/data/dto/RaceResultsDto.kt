package eu.pitlap.shared.race.data.dto

internal data class RaceResultsDto(
    val results: List<RaceResultDto>
)

internal data class RaceResultDto(
    val position: Int,
    val headshotURL: String,
    val points: Int,
    val gridPosition: Int,
    val fullName: String,
    val classifiedPosition: String,
    val teamName: String
)

