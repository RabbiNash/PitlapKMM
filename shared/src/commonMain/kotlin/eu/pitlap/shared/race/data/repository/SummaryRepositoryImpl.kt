package eu.pitlap.shared.race.data.repository

import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.race.data.source.SummaryDataSource
import eu.pitlap.shared.race.data.source.SummaryDataSourceImpl
import eu.pitlap.shared.race.domain.repository.SummaryRepository
import eu.pitlap.shared.race.domain.mapper.toRaceSummaryModel
import eu.pitlap.shared.race.domain.mapper.toTrackSummaryModel
import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel

internal class SummaryRepositoryImpl(
    private val dataSource: SummaryDataSource = SummaryDataSourceImpl()
): SummaryRepository {
    override suspend fun getRaceSummary(year: Int, round: Int): RaceSummaryModel {
        return when(val result = dataSource.getRaceSummary(year, round)) {
            is Result.Success -> result.data.toRaceSummaryModel()
            is Result.Error -> throw result.error.toThrowable()
        }
    }

    override suspend fun getTrackSummary(trackName: String): TrackSummaryModel {
        return when(val result = dataSource.getTrackSummary(trackName)) {
            is Result.Success -> result.data.toTrackSummaryModel()
            is Result.Error -> throw result.error.toThrowable()
        }
    }
}
