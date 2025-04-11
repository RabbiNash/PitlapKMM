package eu.pitlap.shared.race.domain.mapper

import eu.pitlap.shared.race.domain.model.CompoundLaps
import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.LapModel

class GroupedLapsMapper {
    fun mapToGroupedLapModels(laps: List<LapModel>): List<GroupedLapModel> {
        return laps.groupBy { it.driver }
            .map { (driver, driverLaps) ->
                GroupedLapModel(
                    driver = driver,
                    fullName = driverLaps.firstOrNull()?.fullName.orEmpty(),
                    headshotUrl = driverLaps.firstOrNull()?.headshotUrl.orEmpty(),
                    compoundLaps = groupLapsByCompound(driverLaps),
                    bestLapTime = findBestLapTime(driverLaps)
                )
            }
            .sortedBy { it.bestLapTime }
    }

    private fun groupLapsByCompound(laps: List<LapModel>): List<CompoundLaps> {
        return laps.groupBy { it.compound }
            .map { (compound, laps) -> CompoundLaps(compound = compound, laps = laps) }
    }

    private fun findBestLapTime(laps: List<LapModel>): String {
        return laps.minByOrNull { it.lapTime }?.lapTime.orEmpty()
    }
}
