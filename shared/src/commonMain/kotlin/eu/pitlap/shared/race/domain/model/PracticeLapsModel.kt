package eu.pitlap.shared.race.domain.model

data class GroupedLapModel(
    val driver: String,
    val fullName: String,
    val headshotUrl: String,
    val compoundLaps: List<CompoundLaps>,
    val bestLapTime: String
)

data class LapModel(
    val driver: String,
    val headshotUrl: String,
    val compound: String,
    val lapTime: String,
    val lapNumber: Int,
    val fullName: String
)

data class CompoundLaps(
    val compound: String,
    val laps: List<LapModel>
)
