package eu.pitlap.shared.standings.data.repository

import eu.pitlap.shared.cache.dao.standings.StandingDAO
import eu.pitlap.shared.cache.factory.DatabaseProvider
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.map
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.standings.data.source.StandingsDataSource
import eu.pitlap.shared.standings.data.source.StandingsDataSourceImpl
import eu.pitlap.shared.standings.domain.mapper.DriverStandingsMapper
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import eu.pitlap.shared.standings.domain.repository.StandingsRepository
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal class StandingsRepositoryImpl(
    private val dataSource: StandingsDataSource = StandingsDataSourceImpl(),
    private val driverStandingsMapper: DriverStandingsMapper = DriverStandingsMapper(),
    private val dao: StandingDAO = DatabaseProvider.getStandingDAO()
): StandingsRepository {
    private val ttl = Duration.parse("PT1H")

    @OptIn(ExperimentalTime::class)
    override suspend fun getDriverStandings(forceRefresh: Boolean): List<DriverStandingModel> {
        val cachedStanding = dao.getDriverStandings()
        val meta = dao.getDriverStandingsMeta()
        val standings = if (meta != null && !forceRefresh && Clock.System.now() - Instant.parse(meta.last_fetched) < ttl) {
            cachedStanding
        } else {
            emptyList()
        }
        return standings.ifEmpty {
            val result = dataSource.getDriverStandings()
                .map { driverStandingsMapper.mapToDriverStandings(it) }

            return when(result) {
                is Result.Success -> {
                    dao.clearAndCreateDriverStandings(result.data)
                    dao.insertDriverStandingMeta(Clock.System.now().toString())
                    result.data
                }
                is Result.Error -> {
                    cachedStanding.ifEmpty {
                        throw result.error.toThrowable()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun getConstructorStandings(forceRefresh: Boolean): List<ConstructorStandingModel> {
        val cachedStanding = dao.getConstructorStandings()
        val meta = dao.getConstructorStandingsMeta()
        val standings = if (meta != null && !forceRefresh && Clock.System.now() - Instant.parse(meta.last_fetched) < ttl) {
            cachedStanding
        } else {
            emptyList()
        }

        return standings.ifEmpty {
            val result = dataSource.getConstructorStandings()
                .map { driverStandingsMapper.mapToConstructorStandings(it) }

            return when(result) {
                is Result.Success -> {
                    dao.clearAndCreateConstructorStandings(result.data)
                    dao.insertConstructorStandingMeta(Clock.System.now().toString())
                    result.data
                }
                is Result.Error -> {
                    cachedStanding.ifEmpty {
                        throw result.error.toThrowable()
                    }
                }
            }
        }
    }
}
