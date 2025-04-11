package eu.pitlap.shared.standings.data.repository

import eu.pitlap.shared.core.domain.map
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.standings.data.source.StandingsDataSource
import eu.pitlap.shared.standings.data.source.StandingsDataSourceImpl
import eu.pitlap.shared.standings.domain.mapper.DriverStandingsMapper
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import eu.pitlap.shared.standings.domain.repository.StandingsRepository

internal class StandingsRepositoryImpl(
    private val dataSource: StandingsDataSource = StandingsDataSourceImpl(),
    private val driverStandingsMapper: DriverStandingsMapper = DriverStandingsMapper()
): StandingsRepository {
    override suspend fun getDriverStandings(): List<DriverStandingModel> {
        val result = dataSource.getDriverStandings()
            .map { driverStandingsMapper.mapToDriverStandings(it) }

        return when(result) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error.toThrowable()
        }
    }

    override suspend fun getConstructorStandings(): List<ConstructorStandingModel> {
        val result = dataSource.getConstructorStandings()
            .map { driverStandingsMapper.mapToConstructorStandings(it) }

        return when(result) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error.toThrowable()
        }
    }
}
