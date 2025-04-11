package eu.pitlap.shared.race.domain.model

data class RaceResultsModel(
    val results: List<RaceResultModel>
)

data class RaceResultModel(
    val position: Int,
    val headshotURL: String,
    val points: Int,
    val gridPosition: Int,
    val fullName: String,
    val classifiedPosition: String,
    val teamName: String
)
