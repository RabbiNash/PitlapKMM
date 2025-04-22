package eu.pitlap.shared.race.data.repository

import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.race.data.source.TrackEventsDataSource
import eu.pitlap.shared.race.data.source.TrackEventsDataSourceImpl
import eu.pitlap.shared.race.domain.mapper.GroupedLapsMapper
import eu.pitlap.shared.race.domain.mapper.toLapModel
import eu.pitlap.shared.race.domain.mapper.toResultsModel
import eu.pitlap.shared.race.domain.mapper.toTopSpeedsModel
import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel
import eu.pitlap.shared.race.domain.model.TopSpeedModel
import eu.pitlap.shared.race.domain.repository.TrackEventsRepository

internal class TrackEventsRepositoryImpl(
    private val dataSource: TrackEventsDataSource = TrackEventsDataSourceImpl(),
    private val groupedLapsMapper: GroupedLapsMapper = GroupedLapsMapper()
): TrackEventsRepository {
    override suspend fun getPracticeLaps(
        year: Int,
        round: Int,
        sessionName: String
    ): List<GroupedLapModel> {
        return when(val result = dataSource.getPracticeLaps(year = year, round = round, sessionName = sessionName)) {
            is Result.Success -> groupedLapsMapper.mapToGroupedLapModels(result.data.laps.map { it.toLapModel() })
            is Result.Error -> throw result.error.toThrowable()
        }
    }

    override suspend fun getQualifyingResults(year: Int, round: Int): List<QualifyingResultModel> {
        return when(val result = dataSource.getQualifyingResults(year = year, round = round)) {
            is Result.Success -> result.data.toResultsModel()
            is Result.Error -> throw result.error.toThrowable()
        }
    }

    override suspend fun getRaceResults(year: Int, round: Int): List<RaceResultModel> {
        return when(val result = dataSource.getRaceResults(year = year, round = round)) {
            is Result.Success -> result.data.toResultsModel()
            is Result.Error -> throw result.error.toThrowable()
        }
    }

    override suspend fun getTopSpeeds(
        year: Int,
        round: Int,
        sessionName: String
    ): List<TopSpeedModel> {
        val name = when(sessionName) {
            "Qualifying" -> "Q"
            "Race" -> "R"
            else -> sessionName
        }
        return when(val result = dataSource.getTopSpeeds(year = year, round = round, sessionName = name)) {
            is Result.Success -> result.data.toTopSpeedsModel()
            is Result.Error -> throw result.error.toThrowable()
        }
    }
}
